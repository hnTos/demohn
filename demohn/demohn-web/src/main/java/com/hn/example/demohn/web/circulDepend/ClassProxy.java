package com.hn.example.demohn.web.circulDepend;

/**
 * @Author: liyb
 * @Date: 2019/6/10
 * @description
 */
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ClassProxy implements InvocationHandler ,AopSonProxy{
    private Object target;
    /**
     * 绑定委托对象并返回一个代理类
     * @param target
     * @return
     */
    public Object bind(Object target) {
        this.target = target;
        //取得代理对象
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),  target.getClass().getInterfaces(), this);   //要绑定接口(这是一个缺陷，cglib弥补了这一缺陷)
    }

    @Override
    public void son() {
        System.out.println("son……");
    }

    @Override
    /**
     * 调用方法
     */
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        Object result=null;
        System.out.println("事物开始");
        //执行方法
        result=method.invoke(target, args);
        System.out.println("事物结束");
        return result;
    }

    @Override
    public Object getProxy() {
        System.out.println("test getProxy");
        return this;
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        System.out.println("test getProxy");
        return this;
    }
}