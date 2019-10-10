package org.gordianknot.conf.web.domain;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
/**
 * 系統用戶表
 * @author aaron
 */
@Getter
@Setter
@Builder
@Document(collection="users")
public class User {
 	
 	@Id
 	private String id;
 	
 	@Field("uname")
 	@Indexed(unique=true, background=true)
 	private String username;
 	
 	@Field("pass")
 	private String pass;
 	
 	/**
 	* 對應的環境集合，能操作幾個環境的數據就配置幾個環境
 	*/
 	@Field("envs")
 	private List<String> envs;
 	
}
