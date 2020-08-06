package com.study.redis.use.string;

import com.study.redis.conf.StaticRedisConfig;

import java.util.List;

/**
 * 阻塞
 */
public class ListBlock {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()->{
            while(true){
                List<String> k1 = StaticRedisConfig.jedis.blpop(0, "k1");
                k1.stream().forEach(value->{
                    System.out.println(value);
                });
            }
        });
        t.start();
        t.join();
    }
}
