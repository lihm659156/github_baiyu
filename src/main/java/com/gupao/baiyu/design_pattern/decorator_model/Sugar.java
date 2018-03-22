package com.gupao.baiyu.design_pattern.decorator_model;


/**
 * 糖
 */
public class Sugar implements ICondiment {

    private IDrink drink;

    public Sugar(IDrink drink) {
        this.drink = drink;
    }

    @Override
    public String name() {
        return this.drink.name() + " + 糖 ";
    }

    @Override
    public double cost() {
        return this.drink.cost() + 1;
    }
}
