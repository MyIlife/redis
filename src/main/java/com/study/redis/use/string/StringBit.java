package com.study.redis.use.string;

import com.study.redis.conf.StaticRedisConfig;

import java.text.DecimalFormat;

/**
 * 二者只是做了反转，都能实现对方的功能
 */
public class StringBit {
    public static String[] users =
            {"user0", "user1", "user2", "user3", "user4", "user5", "user6", "user7", "user8", "user9"};
    public static final int DAYS = 365;


    public static void main(String[] args) {
        StaticRedisConfig.jedis.flushAll();
        //active();
        //loginInfo();
        countLoginDays();
    }

    /**
     * 统计用户活跃度
     * 统计标准：10天内所有用户登录天数和总天数的比例
     */
    public static void active() {
        for (int i = 0; i < DAYS; i++) {
            for (int j = 0; j < users.length; j++) {
                int value;
                if (j == 3 || j == 5 || j == 6) {
                    value = Math.random() > 0.8 ? 1 : 0;
                } else {
                    value = Math.random() > 0.1 ? 1 : 0;
                }

                StaticRedisConfig.jedis.setbit(users[j], i, String.valueOf(value));
            }
        }
        int loginDay = 0;
        for (int i = 0; i < users.length; i++) {
            loginDay += StaticRedisConfig.jedis.bitcount(users[i]);
            Long userDays = StaticRedisConfig.jedis.bitcount(users[i]);
            String a = new DecimalFormat("0.0").format((float) userDays / (float) DAYS);
            System.out.println("用户" + users[i] + "登录总天数：" + userDays + "，活跃度：" + a);
        }
        System.out.println("用户总活跃度：" + new DecimalFormat("0.0").format((float) loginDay / (float) (users.length * DAYS)));
        //用户登录情况
        for (int i = 0; i < DAYS; i++) {
            System.out.println("第" + i + "登录情况====================");
            for (int j = 0; j < users.length; j++) {
                System.out.println("用户[" + users[j] + "]" + (StaticRedisConfig.jedis.getbit(users[j], i) == true ? "登录" : "未登录"));
            }
        }


    }

    /**
     * 统计每天用户登录情况
     */
    public static void loginInfo() {
        for (int i = 0; i < DAYS; i++) {
            for (int j = 0; j < users.length; j++) {
                StaticRedisConfig.jedis.setbit("第" + i + "天", j, Math.random() > 0.2 ? "1" : "0");
            }
        }
        for (int i = 0; i < DAYS; i++) {
            System.out.println("第" + i + "登录情况====================");
            for (int j = 0; j < users.length; j++) {
                System.out.println("用户[" + users[j] + "]" + (StaticRedisConfig.jedis.getbit("第" + i + "天", j) == true ? "登录" : "未登录"));
            }
        }
    }
    /**
     * 统计某一天内所有用户都登录的那天
     */
    public static void countLoginDays(){
        //1.如果是天作为key
        loginInfo(); // 构建存储
        for (int i = 0; i < DAYS; i++) {
            Long bitcount = StaticRedisConfig.jedis.bitcount("第" + i + "天");
            long userSum = (long)users.length;
            if(userSum==bitcount){
                System.out.println("第" + i + "天所有用户都登录了");
            }
        }
    }
}
