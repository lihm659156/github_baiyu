package com.gupao.baiyu.design_pattern.observer_model.template_2;

import java.lang.reflect.Method;

public class Test {

    public static void main(String[] args) {

        Subject subject = new Subject();

        Observer observer = new Observer();
        try {
            Method method = Observer.class.getMethod("notiry",new Class<?>[]{Event.class});
            subject.add(observer,method,SubjectEnum.ADD);
            subject.add(observer,method,SubjectEnum.REMOVE);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        subject.add();
        subject.remove();
    }
}
