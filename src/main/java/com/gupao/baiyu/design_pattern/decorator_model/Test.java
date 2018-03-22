package com.gupao.baiyu.design_pattern.decorator_model;

public class Test {

    public static void main(String[] args) {
        IDrink drink = new Coffee();
        System.out.println(drink.name());
        System.out.println(drink.cost());

        ICondiment condiment = new Milk(drink);
        System.out.println(condiment.name());
        System.out.println(condiment.cost());

        ICondiment condiment2 = new Sugar(condiment);
        System.out.println(condiment2.name());
        System.out.println(condiment2.cost());

    }

}
