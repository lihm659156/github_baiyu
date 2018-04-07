package com.gupao.baiyu.redis.jedis;

import java.util.*;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.*;

import java.util.HashSet;

public class Test {


    public static void main(String[] args) throws Exception {

        String masterName = "mymaster";
        Set<String> sentinels = new HashSet<String>();
        sentinels.add("192.168.120.41:26379");
        GenericObjectPoolConfig gPoolConfig = new GenericObjectPoolConfig();
        gPoolConfig.setMaxTotal(20);
        gPoolConfig.setMaxIdle(10);
        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool(masterName, sentinels, gPoolConfig);
        HostAndPort hostAndPort = jedisSentinelPool.getCurrentHostMaster();
        Jedis jedis = jedisSentinelPool.getResource();

        System.out.println(jedis);


        Jedis jedis1 = new Jedis("192.168.120.41",26379);

        List<Map<String, String>> list = jedis1.sentinelSlaves(masterName+"123");

        System.out.println(list);

//        try{
//            int port = 26479;
//            Jedis sentinelJedis = new Jedis("192.168.120.41",port );
//            System.out.println(port);
//            sentinelJedis.subscribe(new JedisPubSubAdapter(),"+switch-master");
//        }catch(Exception ex){
//
//        }

//        jedis.close();

        Thread.sleep(1000000000);
    }

    static class JedisPubSubAdapter extends JedisPubSub{
        @Override
        public void onMessage(String channel, String message) {
            System.out.println(Thread.currentThread().getName() + " " + message);
        }
    }

}
