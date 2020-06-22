package com.aaron.zuul_demo.rule;
import com.aaron.zuul_demo.support.RibbonFilterContextHolder;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.Server;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 灰度發佈轉發規則，基於RoundRobinRule規則改造
 *
 * @author aaron
 */
public class GrayPushRule extends AbstractLoadBalancerRule {
    private AtomicInteger nextServerCyclicCounter;
    private static final boolean AVAILABLE_ONLY_SERVERS = true;
    private static final boolean ALL_SERVERS = false;
    private static Logger log = LoggerFactory.getLogger(RoundRobinRule.class);

    /**
     * Instantiates a new Gray push rule.
     */
    public GrayPushRule() {
        this.nextServerCyclicCounter = new AtomicInteger(0);
    }

    /**
     * Instantiates a new Gray push rule.
     *
     * @param lb the lb
     */
    public GrayPushRule(ILoadBalancer lb) {
        this();
        this.setLoadBalancer(lb);
    }

    /**
     * Choose server.
     *
     * @param lb  the lb
     * @param key the key
     * @return the server
     */
// 選擇可用的服務
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            log.warn("no load balancer");
            return null;
        } else {
            /**
             * 具體用戶選擇灰度服務的邏輯
             * 當前有灰度的用戶和灰度的服務配置信息，並且灰度的服務在所有服務中則返回該灰度服務給用戶
             */
            String curUserId = RibbonFilterContextHolder.getCurrentContext().get("userId");
            String userIds = RibbonFilterContextHolder.getCurrentContext().get("userIds");
            String servers = RibbonFilterContextHolder.getCurrentContext().get("servers");
            System.err.println(Thread.currentThread().getName()+"\t"+ servers + "\t登入\t"+ curUserId + "\tApollo\t"+ userIds);
                
            // 符合灰度發布使用資格
            if (StringUtils.isNotBlank(servers)) {
            	List<String> grayServers = Arrays.asList(servers.split(","));
                 if (StringUtils.isNotBlank(userIds) && StringUtils.isNotBlank(curUserId)) {
                     String[] uids = userIds.split(",");
                     if (Arrays.asList(uids).contains(curUserId)) {
                         List<Server> allServers = lb.getAllServers();
                         for (Server server : allServers) {             	 
                             // if (grayServers.contains(server.getHostPort())) {
                        	 // Eureka 服務名預設為大寫，透過工具類變為小寫；規範服務名皆以小寫命名
                             if (grayServers.contains(StringUtils.lowerCase(server.getMetaInfo().getAppName()))) {
                            	 System.err.println("灰度發布服務為\t" + servers + "\t登入\t"+ curUserId + "\tApollo\t"+ userIds);
                                 return server;
                             }
                         }
                     }
                 }
 			}
            
         // 不符合灰度發布使用資格
            Server server = null;
            int count = 0;
            while(true) {
                if (server == null && count++ < 10) {
                    List<Server> reachableServers = lb.getReachableServers();
                    List<Server> allServers = lb.getAllServers();
                    if (StringUtils.isNotBlank(servers)) {
                        // 移除已經設置為灰度發佈的服務信息
                    	 System.err.println("移除已經設置為灰度發佈的服務資訊");
                    	 reachableServers = removeServer(reachableServers, servers);
                         allServers = removeServer(allServers, servers);
                    }
                    int upCount = reachableServers.size();
                    int serverCount = allServers.size();
                    if (upCount != 0 && serverCount != 0) {
                        int nextServerIndex = this.incrementAndGetModulo(serverCount);
                        server = (Server)allServers.get(nextServerIndex);
                        if (server == null) {
                            Thread.yield();
                        } else {
                            if (server.isAlive() && server.isReadyToServe()) {
                                return server;
                            }
                            server = null;
                        }
                        continue;
                    }
                    // 不符合灰度發布使用資格；返回 null，可透過錯誤回應機制
                    System.err.println("不符合灰度發布使用資格");
                    log.warn("No up servers available from load balancer: " + lb);
                    return null;
                }
                if (count >= 10) {
                    log.warn("No available alive servers after 10 tries from load balancer: " + lb);
                }
                return server;
            }
        }
    }
    // 移除已經設置成灰度發布的服務
    private List<Server> removeServer(List<Server> allServers, String servers) {
        List<Server> newServers = new ArrayList<Server>();
        List<String> grayServers = Arrays.asList(servers.split(","));
        for (Server server : allServers) {
//            String hostPort = server.getHostPort();
        	  String appName = StringUtils.lowerCase(server.getMetaInfo().getAppName());        	  
            // if (!grayServers.contains(hostPort)) {
            if (!grayServers.contains(appName)) {	
                newServers.add(server);
            }
        }
        return newServers;
    }
    
    private int incrementAndGetModulo(int modulo) {
        int current;
        int next;
        do {
            current = this.nextServerCyclicCounter.get();
            next = (current + 1) % modulo;
        } while(!this.nextServerCyclicCounter.compareAndSet(current, next));
        return next;
    }
    
    public Server choose(Object key) {
        return this.choose(this.getLoadBalancer(), key);
    }
    
    public void initWithNiwsConfig(IClientConfig clientConfig) {
    }
}
