package com.gupao.baiyu.design_pattern.proxy_model.dynamic_proxy.jdk_proxy;

public class Person implements Food {

    public void eat() {
        System.out.println("饿了，要去饭店吃饭！");
    }

    public void water() {
        System.out.println("渴了，要去商店买水！");
    }
}
