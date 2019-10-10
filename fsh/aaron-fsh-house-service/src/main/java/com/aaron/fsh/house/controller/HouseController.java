package com.aaron.fsh.house.controller;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.gordianknot.common.anno.ApiRateLimit;
import org.gordianknot.common.base.ResponseData;
import com.aaron.fsh.house.po.HouseInfo;
import com.aaron.fsh.house.service.HouseService;
import com.aaron.mqclient.TransactionMqRemoteClient;
import com.aaron.mqclient.dto.TransactionMessage;
/**
 * 房產 API
 * @author aaron
 **/
@RestController
@RequestMapping("/house")
public class HouseController {
 	
 	@Autowired
 	private HouseService houseService;
 	@Value("${server.port}")
 	private String serverPort;
 	@GetMapping("/list/{eid}/{uid}")
 	public ResponseData hosueList(@PathVariable("eid")Long eid, @PathVariable("uid")String uid) {
 		return ResponseData.ok(houseService.queryAll(eid, uid));
 	}
 	
 	/**
 	* 獲取房產信息
 	* @param houseId 房產編號
 	* @return
 	*/
 	@ApiRateLimit(confKey = "open.api.hosueInfo")
 	@GetMapping("/{houseId}")
 	public ResponseData hosueInfo(@PathVariable("houseId")Long houseId, HttpServletRequest request) {
 		String uid = request.getHeader("uid");
 		System.err.println("==="+uid);
 		return ResponseData.ok(houseService.getHouseInfo(houseId));
 	}
 	@GetMapping("/hello")
 	public String hello() throws  Exception {
 		System.err.println("call hello");
 		//Thread.sleep(6000);
 		return "Hello"+serverPort;
 	}
 	@ApiRateLimit(replenishRate=10, burstCapacity=100)
 	@GetMapping("/data")
 	public HouseInfo getData(@RequestParam("name") String name) {
 		return new HouseInfo(1L, "上海", "虹口", "東體小區");
 	}
 	@GetMapping("/data/{name}")
 	public String getData2(@PathVariable("name") String name) {
 		return name;
 	}
 	@PostMapping("/save")
 	public Long addData(@RequestBody HouseInfo houseInfo) {
 		System.out.println(houseInfo.getName());
 		return 1001L;
 	}
 	/**
 	* 測試可靠消息發送
 	* @return
 	*/
 	@GetMapping("/sendMessage")
 	public Object sendMessage() {
 		return houseService.update(new HouseInfo(1L, "上海", "虹口", "東體小區"));
 	}
}
