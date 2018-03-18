package com.gupao.baiyu.design_pattern.singleton_model;

import java.lang.reflect.Constructor;

/**
 * 反射入侵
 */
public class ReflectionIntrusionTest {

    public static void main(String[] args) throws Exception {
        Class<?> clazz = Singleton_1.class;
        // 通过反射，拿到私有化构造函数
        Constructor constructor = clazz.getDeclaredConstructor(null);
        // 设置强制访问
        constructor.setAccessible(true);
        // 实例化对象
        Singleton_1 single = (Singleton_1)constructor.newInstance();
        System.out.println(single);
        System.out.println(constructor.newInstance());
    }

}
