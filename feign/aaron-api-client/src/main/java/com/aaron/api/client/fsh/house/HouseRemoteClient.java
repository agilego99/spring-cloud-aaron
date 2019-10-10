package com.aaron.api.client.fsh.house;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.aaron.api.client.config.FeignConfiguration;
import com.aaron.api.client.fsh.house.dto.HouseInfoDto;
import com.aaron.api.client.fsh.house.dto.HouseListDto;
/**
 * 房生活房產服務 API 調用客戶端
 * @author aaron
 **/
//, fallback = HouseRemoteClientHystrix.class
@FeignClient(value = "fsh-house", path = "/house", configuration = FeignConfiguration.class
 		, fallbackFactory = HouseRemoteClientFallbackFactory.class)
public interface HouseRemoteClient {
 	
 	/**
 	* 獲取企業下某用戶的有效房產信息
 	* @param eid	企業編號
 	* @param uid	用戶編號
 	* @return
 	*/
 	@GetMapping("/list/{eid}/{uid}")
 	HouseListDto hosueList(@PathVariable("eid")Long eid, @PathVariable("uid")String uid);
 	
 	/**
 	* 獲取房產詳細信息
 	* @param houseId 房產編號
 	* @return
 	*/
 	@GetMapping("/{houseId}")
 	HouseInfoDto hosueInfo(@PathVariable("houseId")Long houseId);
 	@GetMapping("/hello")
    String hello();
}
