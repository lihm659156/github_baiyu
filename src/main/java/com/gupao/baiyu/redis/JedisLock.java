package com.gupao.baiyu.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.UUID;

public class JedisLock {

    // 保证set和过期时间的原子性
    // jedis.set("key111","value111","NX","PX", 100000);
    // 保证setnx和expire命令原子执行
    public static long setnxtime(Jedis jedis, String key, String value, String timeout){
        String lua = "local key = KEYS[1];\n" +
                "local value = KEYS[2];\n" +
                "local time = KEYS[3];\n" +
                "local result = redis.call(\"setnx\", key, value);\n" +
                "local result2 = redis.call(\"expire\", key, time);\n" +
                "return result;";
        Object obj = jedis.eval(lua , 3, key, value, timeout);
        return (Long)obj;
    }

    /**
     * 获得lock
     * @param key
     * @param timeout
     * @return
     */
    public static String getLock(String key, long timeout){
        try{
            System.out.println(Thread.currentThread().getId() + " 开始获得锁...");

            Jedis jedis = RedisManager.getJedis();
            String value = UUID.randomUUID().toString();
            long endTime = System.currentTimeMillis() + timeout;
            // 是否超时
            while ((endTime - System.currentTimeMillis()) > 0){
                if(JedisLock.setnxtime(jedis, key, value, String.valueOf(timeout)) > 0){
                    System.out.println(Thread.currentThread().getId() + " 获得锁成功...");
                    return value;
                }
                // 每一秒钟重试一次
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getId() + " 获得锁中...");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 释放lock
     * @param key
     */
    public static boolean relaseLock(String key, String value){
        try{
            if(value == null){
                return false;
            }
            System.out.println(Thread.currentThread().getId() +" 开始释放锁...");
            Jedis jedis = RedisManager.getJedis();
            jedis.watch(key);
            while (true){
                if(value.equals(jedis.get(key))){
                    // 当前线程，获得锁，与redis中存的是一个线程
                    Transaction transaction = jedis.multi();
                    // 删除key
                    transaction.del(key);
                    List<Object> list = transaction.exec();
                    if(list == null){
                        continue;
                    }
                    System.out.println(Thread.currentThread().getId() +" 释放锁成功...");
                    return true;
                }
                jedis.unwatch();
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getId() +" 释放锁中...");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        String key = "lock:business:order";
        long timeout = 5000;

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                String val = JedisLock.getLock(key,timeout);
                try {
                    System.out.println("执行业务1");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    JedisLock.relaseLock(key,val);
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                String val = JedisLock.getLock(key,timeout);
                try {
                    System.out.println("执行业务2");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    JedisLock.relaseLock(key,val);
                }
            }
        });

        t1.start();
        t2.start();


//        String val = JedisLock.getLock(key,timeout);
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        JedisLock.relaseLock(key,val);

    }

}
