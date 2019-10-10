package com.aaron.feign_demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aaron.feign_demo.remote.UserRemoteClient;


@RestController
public class DemoController {
 	@Autowired
 	private UserRemoteClient userRemoteClient;
 	@GetMapping("/callHello")
 	public String callHello() {
 		System.err.println("進來了。。。。。");
 		//System.err.println(2/0);
 		String result = userRemoteClient.hello();
 		System.out.println(" 調用結果：" + result);
 		return result;
 	}
}
