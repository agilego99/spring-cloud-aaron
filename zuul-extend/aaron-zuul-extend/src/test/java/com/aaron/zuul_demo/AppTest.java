package com.aaron.zuul_demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {
    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @Test
    public void contextLoads() {
        redisTemplate.opsForValue().set("test","helloworld");
        String test = (String) redisTemplate.opsForValue().get("test");
        System.err.println(test);
    }

}
