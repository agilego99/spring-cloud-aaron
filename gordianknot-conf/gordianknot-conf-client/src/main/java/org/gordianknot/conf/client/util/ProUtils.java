package org.gordianknot.conf.client.util;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.gordianknot.conf.client.common.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class ProUtils {
    
    final static Logger LOGGER = LoggerFactory.getLogger(ProUtils.class);
    
    private ProUtils() {
        
    }
    private static Properties propertie = null;
    
    static {
        try {
            propertie = new Properties();
            InputStream inputStream = ProUtils.class.getResourceAsStream("/application.properties");
            BufferedReader buff = new BufferedReader(new InputStreamReader(inputStream));
            propertie.load(buff);
            inputStream.close();
        } catch (Exception e) {
            //如果環境變量中設置了則可以不用 gordianknot-conf.properties中的配置
            String zkUrl = System.getProperty(Constant.ZK_URL);
            if (zkUrl == null) {
                LOGGER.error("加載application.properties文件出錯", e);
                throw new RuntimeException("加載application.properties文件出錯", e);
            }
        }
    }
    
    public static String getProperty(String key) {
        return propertie.getProperty(key);
    }
}
