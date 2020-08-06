package com.study.redis.conf;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class StaticRedisConfig {
    public static Jedis jedis;
    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(8);
        jedisPoolConfig.setMaxWaitMillis(30);
        JedisPool j = new JedisPool(jedisPoolConfig,"10.108.11.65",6379,30,null,1);
        jedis = j.getResource();
    }
}
