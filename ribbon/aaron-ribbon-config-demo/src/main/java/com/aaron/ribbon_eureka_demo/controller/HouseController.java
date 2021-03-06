package com.aaron.ribbon_eureka_demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aaron.ribbon_eureka_demo.dto.HouseInfo;

@RestController
public class HouseController {

	@GetMapping("/house/data") 	
	public HouseInfo getData(@RequestParam("name") String name) {
	    return new HouseInfo(1L, "台北", "松山區", "小巨蛋");
	}  
	
}
