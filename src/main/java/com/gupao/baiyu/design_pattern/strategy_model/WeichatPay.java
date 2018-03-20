package com.gupao.baiyu.design_pattern.strategy_model;

public class WeichatPay implements Payment {

    @Override
    public PayState pay(String uid, double amout) {
        System.out.println("欢迎使用微信支付");
        System.out.println("查询余额中...");
        return new PayState(200,"扣款"+amout,"微信支付成功");
    }
}
