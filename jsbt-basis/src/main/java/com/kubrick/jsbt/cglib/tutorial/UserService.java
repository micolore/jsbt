package com.kubrick.jsbt.cglib.tutorial;

/**
 * @author k
 * @version 1.0.0
 * @ClassName UserService
 * @description: TODO
 * @date 2021/3/13 下午7:13
 */
public class UserService {
    @MyMethod
    public Object doSomething1(Object param) {
        System.out.println("@MyMethod.doSomething1 is work:" + param);
        return param;
    }

    public Object doSomething2(Object param) {
        System.out.println("@MyMethod.doSomething2 is work:" + param);
        return param;
    }

    @MyMethod
    public Object doSomething3(Object param) {
        System.out.println("@MyMethod.doSomething3 is work:" + param);
        return param;
    }
}
