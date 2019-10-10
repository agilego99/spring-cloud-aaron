package org.gordianknot.conf.web.listener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.gordianknot.conf.client.common.EnvConstants;
import org.gordianknot.conf.client.util.CommonUtil;
import org.gordianknot.conf.client.zk.ZkClient;
import org.gordianknot.conf.web.domain.User;
import org.gordianknot.conf.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
/**
* 啓動監聽器
* @author aaron
*
*/
@WebListener
public class StartServletContextListener implements ServletContextListener {
    @Value("${zookeeper.url}")
    private String zkUrl;
    
    @Value("${server.port}")
    private String port;
    
    @Autowired
    private UserService userService;
    
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        
    }
    @Override
    public void contextInitialized(ServletContextEvent event) {
        //初始化默認管理員賬號（預設值）
        if (!userService.exists("root")) {
            userService.save(User.builder().username("root").pass("999999").envs(EnvConstants.ENVS).build());
        }
        
        System.setProperty("zookeeper.url", zkUrl);
        ZkClient zkClient = CommonUtil.getZkClient();
        zkClient.createRootPath();
        if (port == null) {
            port = "8080";
        }
        String localIp = CommonUtil.getLocalIp() + ":" + port;
        zkClient.createServerList(localIp);
    }
}
