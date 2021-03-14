package com.kubrick.jsbt.cglib.tutorial;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author k
 * @version 1.0.0
 * @ClassName Main
 * @description: TODO
 * @date 2021/3/13 下午7:12
 */
public class Main {
    public static void main(String[] args) {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
        enhancer.setCallback(new MethodInterceptor() {

            /**
             *
             * @param obj  为由CGLib动态生成的代理类实例
             * @param method 为上文中实体类所调用的被代理的方法引用
             * @param args 为参数值列表
             * @param proxy 为生成的代理类对方法的代理引用
             * @return
             * @throws Throwable
             */
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

                boolean isAnnotationPresent = method.isAnnotationPresent(MyMethod.class);

                if (isAnnotationPresent) {
                    System.out.println("before Annotations MyMethod is work : " + method.getName());
                }
                // 调用代理类实例上的proxy方法的父类方法
                Object returnObj = proxy.invokeSuper(obj, args);
                if (isAnnotationPresent) {
                    System.out.println("after Annotations MyMethod is work : " + method.getName());
                }
                return returnObj;
            }
        });
        UserService userService = (UserService) enhancer.create();
        userService.doSomething1("hello world");
        System.out.println();
        userService.doSomething2("hello world");
        System.out.println();
        userService.doSomething3("hello world");

    }
}
