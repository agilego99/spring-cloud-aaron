package org.gordianknot.conf.web.domain;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
/**
* 修改日誌記錄
* @author aaron
*/
@Getter
@Setter
@Builder
@Document(collection="update_log")
public class UpdateLog {
    
    @Id
    private String id;
    
    /**
     * 用戶
     */
    @Field("uname")
    private String username;
    
    /**
     * 修改對象（Conf）的ID
     */
    @Indexed(background=true)
    @Field("u_obj_id")
    private String updateObjId;
    
    /**
     * 原始值
     */
    @Field("o_val")
    private Object oldValue;
    
    /**
     * 修改之後的值
     */
    @Field("n_val")
    private Object newValue;
    
    /**
     * 修改時間
     */
    @Field("u_date")
    private Date updateTime;
    
    /**
     * 修改描述
     */
    @Field("u_desc")
    private String updateDesc;
    
}
