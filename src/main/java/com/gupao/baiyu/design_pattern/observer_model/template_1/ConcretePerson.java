package com.gupao.baiyu.design_pattern.observer_model.template_1;

/**
 * 具体订阅者
 */
public class ConcretePerson implements Person {

    private String name;

    ConcretePerson(String name){
        this.name = name;
    }

    public String getNewspaper(PostOffice postOffice) {
        ConcretePostOffice concretePostOffice = (ConcretePostOffice)postOffice;
        String status = concretePostOffice.getStatus();
        String content = concretePostOffice.getContent();
        String str = "status:" + status + ", content:" + content;
        System.out.println(name + "," +str);
        return  str;

    }

}
