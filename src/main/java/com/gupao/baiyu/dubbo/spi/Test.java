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
    }

}
