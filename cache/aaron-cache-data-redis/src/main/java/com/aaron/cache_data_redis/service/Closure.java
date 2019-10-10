package com.aaron.cache_data_redis.service;

// 方法回調接口，用於執行回調的業務邏輯
public interface Closure<O, I> {
	public O execute(I input);
}