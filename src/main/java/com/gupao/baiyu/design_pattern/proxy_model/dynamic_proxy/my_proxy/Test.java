package com.gupao.baiyu.design_pattern.proxy_model.dynamic_proxy.my_proxy;

import java.lang.reflect.Method;

public class Test {

    public static void main(String[] args) {
        String proxyJavaStr = "";
        //1.拿到被代理对象的引用，并且获取到它的所有接口，反射获取

        Method[] methods = Person.class.getMethods();
        if(methods != null && methods.length > 0){
            for(Method method : methods){
                
            }
        }


        //2.JDK Proxy 类重新生成一个新的类，同时新的类要实现被代理类所有实现方法
        //3.动态的生成java代码，把新的代理逻辑加入到新的类中
        //4.编译java文件，生成.class文件
        //5.使用自己定义的类加载器，加载class文件
    }

}
