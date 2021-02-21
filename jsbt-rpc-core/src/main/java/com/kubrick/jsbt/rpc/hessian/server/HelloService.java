package com.kubrick.jsbt.rpc.hessian.server;

/**
 * @author k
 */
public interface HelloService {

    public String helloWorld(String message);

    public User getMyInfo(User user);
}
