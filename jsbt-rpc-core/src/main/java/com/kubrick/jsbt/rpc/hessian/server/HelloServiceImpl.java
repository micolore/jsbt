package com.kubrick.jsbt.rpc.hessian.server;

import com.caucho.hessian.server.HessianServlet;

/**
 * @author k
 * @version 1.0.0
 * @ClassName HelloServiceImpl
 * @description: TODO
 * @date 2021/2/22 上午12:09
 */
public class HelloServiceImpl extends HessianServlet implements HelloService {

    private static final long serialVersionUID = -3537274030227675984L;

    @Override
    public String helloWorld(String message) {
        return "Hello, " + message;
    }

    @Override
    public User getMyInfo(User user) {
        if (null == user) {
            return new User();
        }

        user.setAge(99);
        return user;
    }

}
