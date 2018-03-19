package com.gupao.baiyu.design_pattern.proxy_model.dynamic_proxy.jdk_proxy;

public class Test {

    public static void main(String[] args) {

        // 一个人饿了要吃饭
        Person person = new Person();

        // 送餐员，送餐到家
        Food obj = (Food)new Delivery().getInstance(person);

        // 调用代理方法
        obj.eat();

        obj.water();

    }

}
