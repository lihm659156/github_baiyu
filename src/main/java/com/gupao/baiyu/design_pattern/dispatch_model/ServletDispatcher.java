package com.gupao.baiyu.design_pattern.dispatch_model;

import com.gupao.baiyu.design_pattern.dispatch_model.action.OrderAction;
import com.gupao.baiyu.design_pattern.dispatch_model.action.UserAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ServletDispatcher {

    List<Handler> list = new ArrayList<Handler>();

    public ServletDispatcher(){
        try {
            Method method = OrderAction.class.getMethod("getOrderById",new Class<?>[]{String.class});
            list.add(new Handler(OrderAction.class.newInstance(),method,"/sd/order.do"));

            Method userMethod = UserAction.class.getMethod("getUserById", new Class<?>[]{String.class});
            list.add(new Handler(UserAction.class.newInstance(),userMethod,"/sd/user.do"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doDispatcher(HttpServletRequest rq, HttpServletResponse rsp){
        // 1.获取用户请求url
        String uri = rq.getRequestURI();

        // 2.根据url找到具体action
        Handler handler = null;
        for(Handler h :list ){
            if(h.getUri().equals(uri)){
                handler = h;
                break;
            }
        }

        // 3.使用反射调用action
        Object result = null;
        try {
            result = handler.getMethod().invoke(handler.getController(),rq.getParameter("param"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 4.返回响应，执行结果
        try {
            rsp.getWriter().write(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Handler{
        private Object controller;
        private Method method;
        private String uri;

        public Handler(Object controller, Method method, String uri) {
            this.controller = controller;
            this.method = method;
            this.uri = uri;
        }

        public Object getController() {
            return controller;
        }

        public void setController(Object controller) {
            this.controller = controller;
        }

        public Method getMethod() {
            return method;
        }

        public void setMethod(Method method) {
            this.method = method;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }
    }

}
