package com.gupao.baiyu.design_pattern.decorator_model;

/**
 * 牛奶
 */
public class Milk implements ICondiment{

    private IDrink drink;

    public Milk(IDrink drink) {
        this.drink = drink;
    }

    @Override
    public String name() {
        return this.drink.name() + " + 奶 +";
    }

    @Override
    public double cost() {
        return this.drink.cost() + 5;
    }
}
