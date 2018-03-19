package com.gupao.baiyu.design_pattern.proxy_model.dynamic_proxy.my_proxy;
import com.gupao.baiyu.design_pattern.proxy_model.dynamic_proxy.my_proxy.Food;
import com.gupao.baiyu.design_pattern.proxy_model.dynamic_proxy.my_proxy.MyInvocationHandler;
import java.lang.reflect.Method;
public class $Proxy0 implements Food{
MyInvocationHandler h;
public $Proxy0(MyInvocationHandler h) {
this.h = h;
}
public void eat() {
try {
Method m = Food.class.getMethod("eat", new Class[]{});
this.h.invoke(this, m, null);
} catch(Throwable e) {
e.printStackTrace();
} 
}
}
