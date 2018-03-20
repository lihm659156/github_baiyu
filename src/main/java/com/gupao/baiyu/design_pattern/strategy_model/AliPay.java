package com.gupao.baiyu.design_pattern.strategy_model;

public class AliPay implements Payment {

    @Override
    public PayState pay(String uid, double amout) {
        System.out.println("欢迎使用阿里支付");
        System.out.println("查询余额中...");
        return new PayState(200,"扣款"+amout,"阿里支付成功");
    }
}
