package com.gupao.baiyu.design_pattern.proxy_model.static_proxy;

/**
 * 附近的快递代收点
 */
public class Shop {

    private Person person;

    Shop(Person person){
        this.person = person;
    }

    public void getExpress(){
        System.out.println("快递代收店，接收快递，并暂时保存");
        person.getExpress();
        System.out.println("收取接收人的钱");
    }

}
