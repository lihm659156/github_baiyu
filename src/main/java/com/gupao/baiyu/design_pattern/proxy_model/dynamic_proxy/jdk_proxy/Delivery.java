package com.gupao.baiyu.design_pattern.proxy_model.dynamic_proxy.jdk_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 送餐员
 */
public class Delivery implements InvocationHandler{

    private Food food;

    public Object getInstance(Food food){
        this.food = food;

        Class<?> clazz = food.getClass();

        for(Class claxx : clazz.getInterfaces()){
            System.out.println(claxx);
        }

        // 使用jdk InvocationHandler 生成代理类
        Object obj = Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);

        return obj;
    }

    // 代理方法
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("送餐员，送餐到家");
        method.invoke(food, args);
        System.out.println("送餐员离开了");
        return null;
    }
}
