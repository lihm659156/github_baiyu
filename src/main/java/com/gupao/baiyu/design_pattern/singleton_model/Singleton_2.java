package com.gupao.baiyu.design_pattern.singleton_model;

/**
 * 懒汉式
 */
public class Singleton_2 {

    // 私有化构造函数
    private Singleton_2(){};

    // 初始化不加载，
    private static Singleton_2 singleton = null;

    public static Singleton_2 getInstance(){
        if(singleton == null){
            singleton = new Singleton_2();
            return singleton;
        }
        return singleton;
    }

    public static void main(String[] args) {

    }

}
