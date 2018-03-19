package com.gupao.baiyu.design_pattern.proxy_model.dynamic_proxy.jdk_proxy;

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

        obj.water();

    }

}
