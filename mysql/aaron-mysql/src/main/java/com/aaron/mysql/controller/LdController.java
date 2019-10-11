package com.aaron.mysql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aaron.mysql.service.LdService;

@RestController
public class LdController {

	@Autowired
	private LdService ldService;
	
	@GetMapping("/test")
	public String test() {
		System.err.println(ldService.count());
		return "success";
	}
	
	@GetMapping("/test1")
	public String test1() {
		ldService.generatePo("com.aaron.mysql.po","aaron", "/Users/aaron/Downloads/aaron-mysql");
		return "success";
	}
}
