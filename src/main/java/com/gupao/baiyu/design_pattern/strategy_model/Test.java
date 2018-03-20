package com.gupao.baiyu.design_pattern.strategy_model;

public class Test {

    public static void main(String[] args) {

        // 方式一
        Order order = new Order("1","20181111",122.22);
//        System.out.println(order.pay(PayType.ALI_PAY));
        System.out.println(order.pay(PayType.get("1")));

        //方式二
        AppContext context = new AppContext(new AliPay());
//        System.out.println(context.pay(order));

        //方案三
        AppContext2 context2 = new AppContext2();
//        System.out.println(order.pay(context2.get(PayType.ALI_PAY)));

    }

}
