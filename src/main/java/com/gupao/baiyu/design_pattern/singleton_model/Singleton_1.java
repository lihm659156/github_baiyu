package com.gupao.baiyu.design_pattern.singleton_model;

import java.io.Serializable;

/**
 * 防止反序列化破坏单例模式
 */
public class Singleton_1 implements Serializable {

    // 初始化加载实例
    private static Singleton_1 singletion = new Singleton_1();

    // 防止反序列化破坏单例模式
    private Object readResolve() {
        return singletion;
    }

    //防止反射入侵
    private static boolean init = false;

    // 私有化构造函数
    private Singleton_1(){
        synchronized (Singleton_1.class){
            if(init = false){
                init = !init;
            }else{
                throw new RuntimeException("单例被侵入！");
            }
        }
    };











    public static Singleton_1 getInstance(){
        return singletion;
    }

    public static void main(String[] args) {
        System.out.println(getInstance());
    }

}
