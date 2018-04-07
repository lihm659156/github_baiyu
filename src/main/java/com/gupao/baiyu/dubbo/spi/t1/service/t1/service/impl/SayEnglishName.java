package com.gupao.baiyu.dubbo.spi.t1.service.t1.service.impl;

import com.gupao.baiyu.dubbo.spi.t1.service.ISayName;

public class SayEnglishName implements ISayName {

    @Override
    public void say() {
        System.out.println("hello spi !!!");
    }
}
