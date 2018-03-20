package com.gupao.baiyu.design_pattern.strategy_model;

import java.util.HashMap;
import java.util.Map;

public class AppContext2 {

    private static Map<PayType, Payment> map = new HashMap<PayType, Payment>();

    static {
        map.put(PayType.ALI_PAY, new AliPay());
        map.put(PayType.WEICHAT_PAY, new WeichatPay());
    }

    public Payment get(PayType type){
        return map.get(type);
    }

}
