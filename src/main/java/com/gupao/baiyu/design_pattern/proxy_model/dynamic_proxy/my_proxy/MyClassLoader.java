package com.gupao.baiyu.design_pattern.proxy_model.dynamic_proxy.my_proxy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * 自定义类加载器
 */
public class MyClassLoader extends ClassLoader {

    private File classPathFile;

    public MyClassLoader(){
        String classPath  = MyClassLoader.class.getResource("").getPath();
        this.classPathFile = new File(classPath);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class clazz = null;
        String className = MyClassLoader.class.getPackage().getName() + "." + name;
        if(classPathFile != null){
            File classFile = new File(classPathFile,name.replaceAll("\\.","/")+".class");
            if (classFile.exists()){
                FileInputStream in = null;
                ByteArrayOutputStream out = null;
                try {
                    in = new FileInputStream(classFile);
                    out = new ByteArrayOutputStream();
                    byte[] buff = new byte[1024];
                    int len;
                    while((len = in.read(buff)) != -1){
                        out.write(buff,0,len);
                    }

                    clazz = defineClass(className ,out.toByteArray(),0,out.size());

                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    try{
                        if(in != null){
                            in.close();
                        }
                        if(out != null){
                            out.close();
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }

                }
            }
        }

        return clazz;
    }
}
