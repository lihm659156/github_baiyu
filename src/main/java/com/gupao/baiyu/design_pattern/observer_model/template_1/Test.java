package com.gupao.baiyu.design_pattern.observer_model.template_1;

public class Test {

    public static void main(String[] args) {

        //邮局
        ConcretePostOffice postOffice = new ConcretePostOffice();

        //订阅者1
        Person person1 = new ConcretePerson("张三");
        //订阅者2
        Person person2 = new ConcretePerson("李四");

        // 订阅
        postOffice.addPersion(person1);
        // 订阅
        postOffice.addPersion(person2);

        postOffice.setContent("报纸内容");
        postOffice.setStatus("报纸到了！");

    }

}
