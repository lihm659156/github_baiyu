package com.gupao.baiyu.design_pattern.singleton_model;

/**
 * 内部类方式
 */
public class Singleton_3 {

    public static void main(String[] args) {
        System.out.println(getInstance());
    }

    // 私有化构造函数
    private Singleton_3(){};

    // 使用静态内部类，调用内部类时加载
    private static class SingletonHolder{
        // 加载内部类时，创建实例，因为只加载一次，所以实例是单例的
        private static Singleton_3 singleton = new Singleton_3();
    }

    // final 保证方法不会被重写
    public static final Singleton_3 getInstance(){
        return SingletonHolder.singleton;
    }

}
