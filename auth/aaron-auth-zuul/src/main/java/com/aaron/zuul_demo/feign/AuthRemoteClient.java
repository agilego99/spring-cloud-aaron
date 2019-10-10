package com.aaron.zuul_demo.feign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.aaron.auth.common.ResponseData;
/** 
 * 認證服務API調用客戶端  
 * @author aaron 
 *
 **/
@FeignClient(value = "aaron-auth-service", path = "/oauth")
public interface AuthRemoteClient {
 	
  /**     
   * 調用認證,獲取token     
   * @param query     
   * @return    
   */
   @PostMapping("/token")
   ResponseData auth(@RequestBody AuthQuery query);
  
}
