package org.gordianknot.conf.web.domain;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.Getter;
import lombok.Setter;
/**
* 配置信息集合
* @author aaron
* @date 2019-06-23
*/
@Getter
@Setter
@Document(collection="conf")
@CompoundIndexes({
    @CompoundIndex(name = "conf_index", def = "{env:1, s_name:1, c_fname:1, key:1}", unique = true, background = true)
})
// REST API 數據結構實體類
public class Conf {
    @Id
    private String id;
    
    /**
     * 環境
     */
    @Field("env")
    private String env;
    
    /**
     * 系統名稱
     */
    @Field("s_name")
    private String systemName;
    
    /**
     * 配置文件名稱
     */
    @Field("c_fname")
    private String confFileName;
    
    /**
     * 配置Key
     */
    @Field("key")
    private String key;
    
    /**
     * 配置Value
     */
    @Field("value")
    private String value;
    
    /**
     * 描述
     */
    @Field("desc")
    private String desc;
    
    /**
     * 創建時間
     */
    @Field("c_date")
    private Date createDate;
    
    /**
     * 修改時間
     */
    @Field("m_date")
    private Date modifyDate;
    
}
