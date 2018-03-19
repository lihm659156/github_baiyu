package com.gupao.baiyu.design_pattern.proxy_model.dynamic_proxy.my_proxy;

import java.lang.reflect.Method;

/**
 * 自定义回调接口
 */
public interface MyInvocationHandler {

    /**
     * 自定义回调函数
     *
     * @param proxy  代理类
     * @param method 被代理类方法
     * @param args   被代理类方法参数
     * @return
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable;

}
