package com.gupao.baiyu.design_pattern.proxy_model.static_proxy;

/**
 * 接收快递的人
 */
public class Person implements Express {

    public void getExpress() {
        System.out.println("今天有一个快递要接收，的早点回去！");
    }
}
