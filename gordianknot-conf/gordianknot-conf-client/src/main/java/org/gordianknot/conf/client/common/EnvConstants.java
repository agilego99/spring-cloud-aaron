package org.gordianknot.conf.client.common;
import java.util.Arrays;
import java.util.List;
public final class EnvConstants {
    
    /**
     * 生產環境
     */
    public static final String PROD = "prod";
    
    /**
     * 線上測試環境
     */
    public static final String ONLINE = "online";
    
    /**
     * 本地開發環境
     */
    public static final String DEV = "dev";
    
    /**
     * 線下測試環境
     */
    public static final String TEST = "test";
    
    public static List<String> ENVS = Arrays.asList(PROD, ONLINE, DEV, TEST);
}
