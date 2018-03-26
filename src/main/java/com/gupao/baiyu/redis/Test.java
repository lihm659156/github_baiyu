package com.gupao.baiyu.redis;

import redis.clients.jedis.Jedis;

public class Test {

    public static void main(String[] args) {
        try{
            Jedis jedis = RedisManager.getJedis();
            String res1 = jedis.set("key111","value111","NX","PX", 100000);
            System.out.println(res1);
            String res2 = jedis.set("key111","value111","NX","PX", 100000);
            System.out.println(res2);
            jedis.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void setnxtime(String key, String value, String timeout){
        String lua = "local key = KEYS[1];\n" +
                    "local value = KEYS[2];\n" +
                    "local time = KEYS[3];\n" +
                    "local result = redis.call(\"setnx\", key, value);\n" +
                    "local result2 = redis.call(\"expire\", key, time);\n" +
                    "return result2;";
        try{
            Jedis jedis = RedisManager.getJedis();
            jedis.eval(lua , 3, key, value, timeout);
            jedis.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private static void hello() {
        try {

            String lua = "local mylist = redis.call(\"lrange\", KEYS[1], 0 ,-1)\n" +
                        "local count = 0;\n" +
                        "for index, key in ipairs(mylist)\n" +
                        "do\n" +
                        "  redis.call(\"incr\", key);\n" +
                        "  count = count + 1\n" +
                        "end\n" +
                        "return count;";


            Jedis jedis = RedisManager.getJedis();
            Object obj = jedis.eval(lua,1,"hot:user:list");
            System.out.println(obj);
            jedis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
