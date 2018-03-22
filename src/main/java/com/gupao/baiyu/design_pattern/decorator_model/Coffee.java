package com.gupao.baiyu.design_pattern.decorator_model;

/**
 * 咖啡
 */
public class Coffee implements IDrink {

    @Override
    public String name() {
        return "普通咖啡";
    }

    @Override
    public double cost() {
        return 10;
    }
}
