package com.gupao.baiyu.design_pattern.observer_model.template_2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 事件监听器
 */
public class EventListener {

    private Map<Enum, Event> events = new HashMap<Enum,Event>();

    // 注册事件方法
    public void add(Object target , Method method , Enum eventType){
        Event event = new Event(target,method);
        events.put(eventType,event);
    }

    public void target(Event e){
        e.setSource(this);
        e.setTime(System.currentTimeMillis());
        try {
            e.getMethod().invoke(e.getTarget(),e);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void target(Enum e){
        if(!this.events.containsKey(e)){
            return;
        }
        Event event = this.events.get(e);
        event.setType(e);
        target(event);
    }

}
