package com.kubrick.jsbt.sso.client.controller;

import com.kubrick.jsbt.sso.client.sso.client.constant.Oauth2Constant;
import com.kubrick.jsbt.sso.client.sso.client.rpc.Result;
import com.kubrick.jsbt.sso.client.sso.client.rpc.RpcAccessToken;
import com.kubrick.jsbt.sso.client.sso.client.rpc.SsoUser;
import com.kubrick.jsbt.sso.client.sso.client.util.Oauth2Utils;
import com.kubrick.jsbt.sso.client.sso.client.util.SessionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author k
 * @version 1.0.0
 * @ClassName AppController
 * @description: TODO
 * @date 2021/2/7 下午4:36
 */
@RestController
@RequestMapping("/app")
public class AppController {

    @Value("${sso.server.url}")
    private String serverUrl;
    @Value("${sso.app.id}")
    private String appId;
    @Value("${sso.app.secret}")
    private String appSecret;

    /**
     * 初始页
     * @param request
     * @param model
     * @return
     */
    @RequestMapping
    public Result index(HttpServletRequest request) {
        SsoUser user = SessionUtils.getUser(request);
        return Result.createSuccess(user);
    }

    /**
     * 登录提交
     *
     * @param username
     * @param password
     * @param request
     * @return
     */
    @RequestMapping("/login")
    public Result login(
            @RequestParam(value = Oauth2Constant.USERNAME, required = true) String username,
            @RequestParam(value = Oauth2Constant.PASSWORD, required = true) String password,
            HttpServletRequest request) {
        Result<RpcAccessToken> result = Oauth2Utils.getAccessToken(serverUrl, appId, appSecret, username, password);
        if (!result.isSuccess()) {
            return result;
        }
        SessionUtils.setAccessToken(request, result.getData());
        return Result.createSuccess().setMessage("登录成功");
    }
}
