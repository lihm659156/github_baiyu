package com.gupao.baiyu.design_pattern.observer_model.template_2;

/**
 * g观察者
 */
public class Observer {

    // 通知方法
    public void notiry(Event e){
        System.out.println("==============目标改变，接到通知================"+e.toString());
    }

}
