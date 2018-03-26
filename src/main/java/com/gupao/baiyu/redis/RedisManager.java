package com.gupao.baiyu.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisManager {


    private static JedisPool jedisPool;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(10);
        jedisPool = new JedisPool(config,"192.168.120.41",6379);
    }

    public static Jedis getJedis() throws Exception{
        if(jedisPool != null){
            return jedisPool.getResource();
        }
        throw new Exception("Jedis get Exception");
    }

}
