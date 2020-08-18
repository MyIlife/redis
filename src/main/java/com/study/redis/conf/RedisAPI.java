package com.study.redis.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.redis.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 低级用法无需指定序列化，因为都是字节发送
 * 高级用法时特殊类型需要指定序列化
 */
@Component
public class RedisAPI {

    @Autowired
    RedisTemplate redisTemplate;//高级api

    @Autowired
    StringRedisTemplate stringRedisTemplate;//高级api

    @Autowired
    ObjectMapper objectMapper;
    public void  testRedis(){

        //低级API
/*        RedisConnection redisConnection = redisTemplate.getConnectionFactory().getConnection();
        redisConnection.set("dddd".getBytes(),"你好".getBytes());
        System.out.println(redisConnection.get("dddd".getBytes()));*/
        Person p = new Person();
        p.setAge(418);
        p.setName("ttt");
        //stringRedisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        Jackson2HashMapper jackson2HashMapper = new Jackson2HashMapper(objectMapper,false);
        stringRedisTemplate.opsForHash().putAll("张三",jackson2HashMapper.toHash(p));
    }

}
