package org.gordianknot.conf.client.common;
import java.util.Arrays;
import java.util.List;
public final class Constant {
    
    /**
     * 根目錄
     */
    public static final String ZK_ROOT_PATH = "/gordianknot_conf";
    
    /**
     * 服務端註冊地址目錄
     */
    public static final String ZK_SERVER_LIST_PATH = ZK_ROOT_PATH + "/reg_servers";
    
    /**
     * 配置支持的數據類型
     */
    public static List<String> VALUE_TYPES = Arrays.asList(
            "java.lang.String",
            "java.lang.Long",
            "java.lang.Double",
            "java.lang.Integer",
            "java.util.Map", "map",
            "int", "long", "double");
    
    /**
     * zk創建臨時節點時檢查的時間,單位秒

     * 創建時需檢查節點是否存在，不存在則創建

     * 由於臨時節點就算客戶端斷開了鏈接，也會出現臨時節點需要延遲N秒後才會消失

     * 所以需要循環檢查是否沒有，如果超過了一定的檢測時間則認為這個節點是存在的臨時節點
     */
    public static final String ZK_CHECK_TEMP_TIME = "zookeeper.check.temp.time";
    
    /**
     * 修改日誌顯示條數
     */
    public static final String SMCONF_LOG_LIMIT = "smconf.log.limit";
    
    /**
     * web後台項目名稱
     */
    public static final String SMCONF_PROJECT_NAME = "smconf.projectName";
    
    /**
     * zookeeper 鏈接地址
     */
    public static final String ZK_URL = "zookeeper.url";
    
    /**
     * 運行環境
     */
    public static final String PROFILE_ACTIVE = "spring.profiles.active";
    
    /**
     * client項目所在的機器ip
     */
    public static final String SERVER_IP = "server.ip";
    
    /**
     * client項目暴露的端口
     */
    public static final String SERVER_PORT = "server.port";
    
    /**
     * rest api token
     */
    public static final String REST_API_TOKEN = "smconf.rest.token";
    
    /**
     * 獲取本地配置數據狀態（local只加載本地默認配置  remote加載配置中心配置）
     */
    public static final String DATA_STATUS = "smconf.data.status";
    /**
     * 獲取是否覆蓋配置中心的配置狀態（true覆蓋  false不覆蓋）
     */
    public static final String OVERWRITE_STATUS = "smconf.overwrite.status";
    
    /**
     * 配置所在包路徑，多個用逗號隔開
     */
    public static final String CONF_PACKAGES = "smconf.conf.package";
    
}
