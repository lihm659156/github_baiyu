package com.gupao.baiyu.design_pattern.observer_model.template_1;

import java.util.ArrayList;
import java.util.List;

/**
 * 邮局
 */
public abstract class PostOffice {

    // 订阅报纸的人
    private List<Person> persions = new ArrayList<Person>();

    // 订阅报纸
    public void addPersion(Person person){
        persions.add(person);
    };

    // 删除报纸
    public void delPersion(Person person){
        persions.remove(person);
    };

    // 报纸到了，通知订阅者到邮局取
    public void notifyPersion(){
        for (Person person:persions) {
            // 让读者取报纸
            person.getNewspaper(this);
        }
    }

}
