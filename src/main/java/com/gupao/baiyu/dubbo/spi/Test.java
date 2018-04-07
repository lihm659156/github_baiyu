package com.gupao.baiyu.dubbo.spi;

import com.gupao.baiyu.dubbo.spi.t1.service.ISayName;

import java.util.ServiceLoader;

public class Test {

    public static void main(String[] args) {
        // spi 接口
        ServiceLoader<ISayName> loaders = ServiceLoader.load(ISayName.class);
        for(ISayName sayName : loaders){
            sayName.say();
        }

        // java spi 缺点：
        // 1.java标准的spi会一次性实例化扩展点所有实现，如果没用用上，则浪费资源
        // 2.如果扩展点加载失败，连扩展点的名称都拿不到。
    }

}
