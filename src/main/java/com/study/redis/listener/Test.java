package com.study.redis.listener;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class Test implements InitializingBean {
    @Autowired
    RedisTemplate redisTemplate;


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("进入redis");
        redisTemplate.opsForValue().set("test1","sdfsdfasdf");
        System.out.println(redisTemplate.opsForValue().get("test1"));
    }
}
