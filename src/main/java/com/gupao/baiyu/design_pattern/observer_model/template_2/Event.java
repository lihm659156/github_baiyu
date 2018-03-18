package com.gupao.baiyu.design_pattern.observer_model.template_2;

import java.lang.reflect.Method;

public class Event {

    // 事件来源
    private Object source;
    // 事件目标
    private Object target;
    // 回调方法
    private Method method;
    // 事件类型
    private Enum type;
    // 事件发生事件
    private long time;

    public Event(Object target, Method method) {
        this.target = target;
        this.method = method;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Enum getType() {
        return type;
    }

    public void setType(Enum type) {
        this.type = type;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Event{" +
                "source=" + source +
                ", target=" + target +
                ", method=" + method +
                ", type=" + type +
                ", time=" + time +
                '}';
    }
}
