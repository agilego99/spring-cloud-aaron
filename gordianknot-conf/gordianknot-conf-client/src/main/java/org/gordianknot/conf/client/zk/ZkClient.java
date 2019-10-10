package org.gordianknot.conf.client.zk;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.gordianknot.conf.client.common.Constant;
import org.gordianknot.conf.client.core.RefreshConfCallBack;
import org.gordianknot.conf.client.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import com.google.common.collect.Lists;
public class ZkClient {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ZkClient.class);
    
    //是否第一次註冊，在需要啓動時加載配置的時候會註冊2次
    private static AtomicBoolean isFirstReg = new AtomicBoolean(true);
    
    private static class ZkClientChild {
        private static ZkClient instance = new ZkClient();
    }
    
    private ZkClient() {}
    
    private static CuratorFramework client = null;
    
    /**
     * 獲取zk client
     * @param url   192.168.10.47:2181
     * @return
     */
    public synchronized static ZkClient getInstance(String url) {
        if (client == null) {
            client = CuratorFrameworkFactory.builder()
                    .connectString(url)
                    .sessionTimeoutMs(1000) //可以解決程序退出後臨時節點沒刪除的問題，盡快刪除
                    .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                    .build();
            client.start();
        }
        return ZkClientChild.instance;
    }
    
    /**
     * 獲取所有服務端地址,服務端啓動時會自動將地址註冊到zk中
     * @return ["192.168.10.100:8080", "192.168.10.101:8080"]
     */
    public List<String> getAllServer() {
        try {
            return client.getChildren().forPath(Constant.ZK_SERVER_LIST_PATH);
        } catch (Exception e) {
            LOGGER.error("", e);
        }
        return null;
    }
    
    /**
     * 創建根目錄
     * @author aaron
     */
    public void createRootPath() {
        try {
            Stat stat = client.checkExists().forPath(Constant.ZK_ROOT_PATH);
            if (stat == null) {
                client.create().withMode(CreateMode.PERSISTENT).forPath(Constant.ZK_ROOT_PATH);
            }
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }
    
    /**
     * 創建API服務節點
     * @author aaron
     * @param address
     */
    public void createServerList(String address) {
        doCreateServerList(client, address);
        addRetryServerListener(address);
    }
    public void doCreateServerList(CuratorFramework client, String address) {
        try {
            Stat stat = client.checkExists().forPath(Constant.ZK_SERVER_LIST_PATH);
            if (stat == null) {
                client.create().withMode(CreateMode.PERSISTENT).forPath(Constant.ZK_SERVER_LIST_PATH);
            }
            String path = CommonUtil.buildPath(Constant.ZK_SERVER_LIST_PATH, address);
            
            //客戶端斷開，然後馬上啓動後節點消失會有一定時間的延遲，這邊循環註冊下
            //這邊是啓動註冊服務，所以肯定是需要進行註冊的
            for (int i = 1; i <= Integer.getInteger(Constant.ZK_CHECK_TEMP_TIME, 30); i++) {
                if (client.checkExists().forPath(path) == null) {
                    client.create().withMode(CreateMode.EPHEMERAL).forPath(path);
                    break;
                }
                Thread.sleep(1000);
                LOGGER.info("註冊服務中...");
            }
            
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }
    
    public void createServerList(CuratorFramework client, String address) {
        doCreateServerList(client, address);
    }
    
    /**
     * 創建節點
     * @author aaron
     * @param path
     * @param mode
     */
    public void createNode(String path, CreateMode mode) {
        try {
            if (mode == CreateMode.EPHEMERAL) {
                for (int i = 1; i <= Integer.getInteger(Constant.ZK_CHECK_TEMP_TIME, 30); i++) {
                    Stat stat = client.checkExists().forPath(path);
                    if (stat == null) {
                        client.create().withMode(mode).forPath(path);
                        isFirstReg.compareAndSet(true, false);
                        break;
                    }
                    //首次註冊才需要判斷，否則就證明節點在之前已經註冊過了，解決項目中註冊2次的問題
                    if (isFirstReg.get()) {
                        Thread.sleep(1000);
                        LOGGER.info("註冊節點中..." + path);
                    }
                }
            } else {
                Stat stat = client.checkExists().forPath(path);
                if (stat == null) {
                    client.create().creatingParentsIfNeeded().withMode(mode).forPath(path);
                }
            }
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }
    
    /**
     * 監控配置是否被修改
     * @author aaron
     * @param path
     * @param callBack
     */
    @SuppressWarnings("resource")
    public void monitor(final String path, final RefreshConfCallBack callBack) {
        try {
            NodeCache nodeChahe = new NodeCache(client, path);
            nodeChahe.getListenable().addListener(new NodeCacheListener() {
                
                public void nodeChanged() throws Exception {
                    callBack.call(path);
                }
            });
            nodeChahe.start();
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }
    
    /**
     * 設置節點的值
     * @author aaron
     * @param path
     * @param value
     */
    public void setValue(String path, String value) {
        try {
            if (StringUtils.hasText(value)) {
                client.setData().forPath(path, value.getBytes());
            }
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }
    
    /**
     * 添加斷開鏈接重新鏈接監聽器
     * @author aaron
     * @param value
     */
    public void addRetryServerListener(String value) {
        ServerConnectionStateListener stateListener = new ServerConnectionStateListener(value, "REG_SERVER", this);
        client.getConnectionStateListenable().addListener(stateListener);
    }
    
    /**
     * 獲取環境下系統下的所有client節點信息
     * @author aaron
     * @param env
     * @param system
     * @return
     */
    public List<String> getClientServers(String env, String system) {
        try {
            return client.getChildren().forPath(CommonUtil.buildPath(Constant.ZK_ROOT_PATH, env, system));
        } catch (Exception e) {
            LOGGER.error("", e);
        }
        return Lists.newArrayList();
    }
}
