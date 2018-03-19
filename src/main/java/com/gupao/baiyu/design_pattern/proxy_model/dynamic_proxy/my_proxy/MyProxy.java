package com.gupao.baiyu.design_pattern.proxy_model.dynamic_proxy.my_proxy;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;

/**
 * 代理类
 */
public class MyProxy {

    private static final String ln = "\r\n";

    /**
     * 生成代理类方法
     *
     * @param loader     被代理类加载器
     * @param interfaces 被代理类接口
     * @param h          回调函数
     * @return
     */
    public static Object newProxyInstance(MyClassLoader loader,
                                          Class<?>[] interfaces,
                                          MyInvocationHandler h) {

        // 1.动态生成java文件

        String src = generateSrc(interfaces[0]);

        try {

            // 2.将java输出硬盘

            String path = MyProxy.class.getResource("").getPath();
            String filePath = path + "$Proxy0.java";
            System.out.println(filePath);
            File file = new File(filePath);
            FileWriter writer = new FileWriter(file);
            writer.write(src);
            writer.close();


            // 3.将java文件编译成class文件

            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);
            Iterable iterable = manager.getJavaFileObjects(file);
            JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, null, null, iterable);
            task.call();
            manager.close();


            // 4.将class文件加载到JVM

            // 5.返回字节码重组后的新的代理对象

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String generateSrc(Class<?> anInterface) {
        StringBuffer sb = new StringBuffer();

        sb.append("package com.gupao.baiyu.design_pattern.proxy_model.dynamic_proxy.my_proxy;" + ln);

        sb.append("import " + anInterface.getName() + ";" + ln);
        sb.append("import " + MyInvocationHandler.class.getName() + ";" + ln);
        sb.append("import java.lang.reflect.Method;" + ln);

        sb.append("public class $Proxy0 implements " + anInterface.getName() + "{" + ln);

        sb.append("MyInvocationHandler h;" + ln);

        sb.append("public $Proxy0(MyInvocationHandler h) {" + ln);
        sb.append("this.h = h;" + ln);
        sb.append("}" + ln);

        for (Method m : anInterface.getMethods()) {

            sb.append("public" + " " + m.getReturnType() + " " + m.getName() + "() {" + ln);

            sb.append("try {" + ln);
            sb.append("Method m = " + anInterface.getName() + ".class.getMethod(\"" + m.getName() + "\", new Class[]{});" + ln);
            sb.append("this.h.invoke(this, m, null);" + ln);
            sb.append("} catch(Throwable e) {" + ln);
            sb.append("e.printStackTrace();" + ln);
            sb.append("} " + ln);

            sb.append("}" + ln);

        }


        sb.append("}" + ln);
        return sb.toString();
    }

    public static void main(String[] args) {
        newProxyInstance(null, Person.class.getInterfaces(), new MyInvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        });
    }

}
