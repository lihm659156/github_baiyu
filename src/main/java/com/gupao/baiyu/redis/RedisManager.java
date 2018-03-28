package com.gupao.baiyu.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RedisManager {

    private static JedisPool jedisPool;

    private static JedisSentinelPool jedisSentinelPool;

    private static ShardedJedisPool shardedJedisPool;

    private static ShardedJedisSentinelPool shardedJedisSentinelPool;

    private static JedisCluster jedisCluster;

    static {
        // 普通
//        JedisPoolConfig config = new JedisPoolConfig();
//        config.setMaxTotal(20);
//        config.setMaxIdle(10);
//        jedisPool = new JedisPool(config,"192.168.120.41",6379);

        // 哨兵连接池
//        String masterName = "mymaster";
//        Set<String> sentinels = new HashSet<String>();
//        sentinels.add("192.168.120.41:26379");
//        sentinels.add("192.168.120.41:26479");
//        sentinels.add("192.168.120.41:26579");
//
//        GenericObjectPoolConfig gPoolConfig = new GenericObjectPoolConfig();
//        gPoolConfig.setMaxTotal(20);
//        gPoolConfig.setMaxIdle(10);
//        jedisSentinelPool = new JedisSentinelPool(masterName, sentinels, gPoolConfig);

        // 分片模式
//        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
//        poolConfig.setMaxTotal(20);
//        poolConfig.setMaxIdle(10);
//        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
//        shards.add(new JedisShardInfo("192.168.120.41", 6379));
//        shards.add(new JedisShardInfo("192.168.120.41", 6479));
//        shardedJedisPool = new ShardedJedisPool(poolConfig, shards);

        // 分片+sentinel
//        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
//        poolConfig.setMaxTotal(20);
//        poolConfig.setMaxIdle(10);
//
//        List<String> masterNames = new ArrayList<String>();
//        masterNames.add("mymaster");
//
//        Set<String> sentinels = new HashSet<String>();
//        sentinels.add("192.168.120.41:26379");
//
//        shardedJedisSentinelPool = new ShardedJedisSentinelPool(masterNames, sentinels, poolConfig, 1000);

        // redis cluster 集群

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(50);
        config.setMinIdle(20);
        config.setMaxWaitMillis(6 * 1000);
        config.setTestOnBorrow(true);

        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        jedisClusterNodes.add(new HostAndPort("192.168.120.41", 6379));
        jedisClusterNodes.add(new HostAndPort("192.168.120.41", 6479));
        jedisClusterNodes.add(new HostAndPort("192.168.120.41", 6579));
        jedisClusterNodes.add(new HostAndPort("192.168.120.41", 6679));
        jedisClusterNodes.add(new HostAndPort("192.168.120.41", 6779));
        jedisClusterNodes.add(new HostAndPort("192.168.120.41", 6879));

        jedisCluster = new JedisCluster(jedisClusterNodes, 2000, 100,config);

    }

    public static Jedis getJedis() throws Exception {
        if (jedisPool != null) {
            return jedisPool.getResource();
        }
        throw new Exception("Jedis get Exception");
    }

    public static Jedis getSentinelJedis() throws Exception {
        if (jedisSentinelPool != null) {
            return jedisSentinelPool.getResource();
        }
        throw new Exception("Jedis get Exception");
    }

    public static Jedis getShardedJedisPool(String key) {
        try {
            if (shardedJedisPool != null) {
                return shardedJedisPool.getResource().getShard("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ShardedJedis getShardedJedisSentinelPool() throws Exception {
        if (shardedJedisSentinelPool != null) {
            return shardedJedisSentinelPool.getResource();
        }
        throw new Exception("Jedis get Exception");
    }

    public static void main(String[] args) {
        try {
//            Jedis jedis = RedisManager.getSentinelJedis();
//            System.out.println(jedis.getClient().getHost() + ":" + jedis.getClient().getPort());
//            jedis.close();

            // 分片模式问题，如果redis挂掉，则连接不上redis
//            String key = "lhm";
//            for (int i = 0; i < 1000; i++) {
//                Jedis jedis = getShardedJedisPool(key);
//                if (jedis == null) {
//                    continue;
//                }
//                try {
//                    System.out.println(jedis.getClient().getHost() + ":" + jedis.getClient().getPort());
//                    jedis.set(key, i + "");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                Thread.sleep(1000);
//            }

            //redis cluster 集群
            jedisCluster.set("test111","123123");
            System.out.println(jedisCluster.get("test111"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
