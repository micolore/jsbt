package com.kubrick.jsbt.sso.server.service;

import com.kubrick.jsbt.sso.client.sso.client.rpc.Result;
import com.kubrick.jsbt.sso.client.sso.client.rpc.SsoUser;

/**
 * @author k
 * @version 1.0.0
 * @ClassName UserService
 * @description: TODO
 * @date 2021/2/7 下午5:29
 */
public interface UserService {
    public Result<SsoUser> login(String username, String password);
}
