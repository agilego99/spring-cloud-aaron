package com.aaron.apollo_springboot.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aaron.apollo_springboot.spring.annotation.SpringValueProcessor;
import com.aaron.apollo_springboot.spring.annotation.property.SpringValue;

/**
 * 配置測試
 * @author Aaron
 *
 */
@RestController
public class ConfigController {

	// 在配置文件中增加對應的配置
	@Value("${gordianknotName:aaron}")
	private String name;

	// 在配置文件中增加對應的配置
	@Value("${gordianknotUrl}")
	private String gordianknotUrl;

	@Autowired
	private SpringValueProcessor springValueProcessor;

	@Autowired
	private ConfigurableBeanFactory beanFactory;

	@GetMapping("/get")
	public String get() {
		return name + gordianknotUrl;
	}

	// 通過調用 「/update」 接口，然後在執行前面的 /get 接口，就可以看到內容已改為新的資料。此為動態修改
	@GetMapping("/update")
	public String update(String value) {
		Collection<SpringValue> targetValues = springValueProcessor.springValueRegistry.get(beanFactory,
				"gordianknotName");
		for (SpringValue val : targetValues) {
			try {
				val.update(value);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return name;
	}
}
