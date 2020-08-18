package com.study.redis;

import com.study.redis.conf.RedisAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Iterator;

@SpringBootApplication
public class RedisApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(RedisApplication.class, args);
/*        Iterator<String> beanNamesIterator = run.getBeanFactory().getBeanNamesIterator();
        while (beanNamesIterator.hasNext()) {
            String next =  beanNamesIterator.next();
            System.out.println(next);
        }*/
        RedisAPI bean = run.getBean(RedisAPI.class);
        bean.testRedis();
    }

}
