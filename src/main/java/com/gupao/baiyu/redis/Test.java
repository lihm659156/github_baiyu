package com.gupao.baiyu.redis;

import redis.clients.jedis.Jedis;

public class Test {

    public static void main(String[] args) {
//        try{
//            Jedis jedis = RedisManager.getJedis();
//            String res1 = jedis.set("key111","value111","NX","PX", 100000);
//            System.out.println(res1);
//            String res2 = jedis.set("key111","value111","NX","PX", 100000);
//            System.out.println(res2);
//            jedis.close();
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
        Object obj = setnxtime("ky111","val212","100000");
        System.out.println(obj);
        Object obj2 = setnxtime("ky111","val212","100000");
        System.out.println(obj2);
    }

    public static Object setnxtime(String key, String value, String timeout){
        String lua = "local key = KEYS[1];\n" +
                "local value = KEYS[2];\n" +
                "local time = KEYS[3];\n" +
                "local result = redis.call(\"setnx\", key, value);\n" +
                "local result2 = redis.call(\"expire\", key, time);\n" +
                "local result3 = redis.call(\"ttl\", key);\n" +
                "return result;";
        Object obj = null;
        Jedis jedis = null;
        try{
            jedis = RedisManager.getJedis();
            obj = jedis.eval(lua , 3, key, value, timeout);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            jedis.close();
        }
        return obj;
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
