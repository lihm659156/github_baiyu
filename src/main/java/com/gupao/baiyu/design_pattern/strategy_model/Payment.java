package com.gupao.baiyu.design_pattern.strategy_model;

/**
 * 支付渠道
 */
public interface Payment {

    public PayState pay(String uid, double amout);

}
