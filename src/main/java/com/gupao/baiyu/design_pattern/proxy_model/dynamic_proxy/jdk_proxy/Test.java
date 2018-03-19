package com.gupao.baiyu.design_pattern.proxy_model.dynamic_proxy.jdk_proxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test {

    public static void main(String[] args) {

//        // 一个人饿了要吃饭
//        Person person = new Person();
//
//        // 送餐员，送餐到家
//        Food obj = (Food)new Delivery().getInstance(person);
//
//        // 调用代理方法
//        obj.eat();
//
//        obj.water();

        //被代理类
        final Food food = new Person();

        Class<?> clazz = food.getClass();

        //代理类
        Food obj = (Food)Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),new InvocationHandler(){
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("送餐员，送餐到家");
                method.invoke(food, args);
                System.out.println("送餐员离开了");
                return null;
            }
        });

        obj.eat();

        System.out.println(obj.getClass());

        // $Proxy0是运行时生成的class文件，我们是无法找到这个class文件
        // 从JVM中取出class文件流，并且输出到硬盘上，方便查看
        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0",new Class<?>[]{Food.class});
        try{
            FileOutputStream out = new FileOutputStream("E://$Proxy0.class");
            out.write(bytes);
            out.close();
        }catch(Exception e){
            e.printStackTrace();
        }



    }

}
