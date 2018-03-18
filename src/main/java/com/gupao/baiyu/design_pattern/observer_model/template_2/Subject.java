package com.gupao.baiyu.design_pattern.observer_model.template_2;


/**
 * 目标
 */
public class Subject extends EventListener {

    public void add(){
        System.out.println("==============目标调用add方法================");
        target(SubjectEnum.ADD);
    }

    public void remove(){
        System.out.println("==============目标调用remove方法================");
        target(SubjectEnum.REMOVE);
    }

}
