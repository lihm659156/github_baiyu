package com.gupao.baiyu.design_pattern.strategy_model;

import java.util.HashMap;
import java.util.Map;

public enum PayType {

    ALI_PAY("1", new AliPay()),
    WEICHAT_PAY("2", new WeichatPay());

    private static Map<String, PayType> map = new HashMap<String,PayType>();

    private String sign;

    private Payment payment;

    PayType(String sign ,Payment payment) {
        this.sign = sign;
        this.payment = payment;
    }

    public Payment getPayment() {
        return payment;
    }

    public String getSign() {
        return sign;
    }

    public static PayType get(String sign){
        if(map.isEmpty()){
            PayType[] payTypes = PayType.values();
            for(PayType payType : payTypes){
                map.put(payType.getSign(),payType);
            }
        }
        return map.get(sign);
    }

}
