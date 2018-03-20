package com.gupao.baiyu.design_pattern.strategy_model;

public class AppContext {

    private Payment payment;

    public AppContext(Payment payment) {
        this.payment = payment;
    }

    public PayState pay(Order order){
        return this.payment.pay(order.getUid() , order.getAmount());
    }

}
