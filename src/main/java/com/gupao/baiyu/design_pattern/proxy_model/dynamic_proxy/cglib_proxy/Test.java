package com.gupao.baiyu.design_pattern.proxy_model.dynamic_proxy.cglib_proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class Test {

    public static void main(String[] args) {

        // cglib 功能
        Enhancer enhancer = new Enhancer();
        // 要为谁代理
        enhancer.setSuperclass(Person.class);
        // 调用回调函数
        enhancer.setCallback(new MethodInterceptor(){
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("代理调用前逻辑");
                methodProxy.invokeSuper(o, objects);
                System.out.println("代理调用后逻辑");
                return null;
            }
        });

        // 创建代理类
        Object obj = enhancer.create();

        System.out.println(obj.getClass());

        Person person = (Person) obj;

        person.getCoffee();

    }

}
