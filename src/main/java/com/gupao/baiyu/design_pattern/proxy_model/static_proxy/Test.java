package com.gupao.baiyu.design_pattern.proxy_model.static_proxy;

public class Test {
    public static void main(String[] args) {
        // 快递接收人
        Express express = new Person();
        // 快递代收店
        Shop shop = new Shop(express);

        shop.getExpress();
    }
}
