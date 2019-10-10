package org.gordianknot.conf.client.core.rest;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/**
* 配置信息集合
* @author aaron
* @date 2019-06-21
*/
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Conf {
    private String id;
    
    /**
     * 環境
     */
    private String env;
    
    /**
     * 系統名稱
     */
    private String systemName;
    
    /**
     * 配置文件名稱
     */
    private String confFileName;
    
    /**
     * 配置Key
     */
    private String key;
    
    /**
     * 配置Value
     */
    private Object value;
    
    /**
     * 描述
     */
    private String desc;
    
    /**
     * 創建時間
     */
    private Date createDate;
    
    /**
     * 修改時間
     */
    private Date modifyDate;
}
