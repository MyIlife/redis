package com.study.redis.use.string;

import com.study.redis.conf.StaticRedisConfig;
import redis.clients.jedis.params.SetParams;

public class TestString {

    public static void main(String[] args) {
        testSet();
    }
    public static void testSet(){
        StaticRedisConfig.jedis.flushAll();
        // 简单set
        StaticRedisConfig.jedis.set("k1","测试简单string-set");
        System.out.println(StaticRedisConfig.jedis.get("k1"));
        // 带参数的set
        // nx 表示key不存在的时候才能set成功
        // xx 表示key存在的时候才能设置成功
        String s = StaticRedisConfig.jedis.set("k1", "test", new SetParams().nx());
        String s1 = StaticRedisConfig.jedis.set("k2", "test", new SetParams().nx());
        System.out.println(s); // null
        System.out.println(s1); // ok

    }
}
