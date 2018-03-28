package com.gupao.baiyu.redis;

import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        String lua = "local num = redis.call('incr', KEYS[1]);\n" +
                "if tonumber(num) == 1 then\n" +
                "  redis.call('expire',KEYS[1],ARGV[1]);\n" +
                "  return 1;\n" +
                "elseif tonumber(num) > tonumber(ARGV[2]) then\n" +
                "  return 0;\n" +
                "else\n" +
                "  return 1;\n" +
                "end";
        try{
            Jedis jedis = RedisManager.getJedis();

            String val = jedis.scriptLoad(lua);

            System.out.println(val);

            List<String> keyList = new ArrayList<String>();
            keyList.add("phone:limit:192.168.1.1");

            List<String> argvList = new ArrayList<String>();
            argvList.add("60000");
            argvList.add("3");

            Object obj = jedis.evalsha("cc369a70dccaacd38cc22611daaf9bbcad19e032",keyList, argvList);

            System.out.println(obj);

            jedis.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }

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
//        Object obj = setnxtime("ky111","val212","100000");
//        System.out.println(obj);
//        Object obj2 = setnxtime("ky111","val212","100000");
//        System.out.println(obj2);
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
