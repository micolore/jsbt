package com.kubrick.jsbt.sso.server.dto;

import com.kubrick.jsbt.sso.client.sso.client.rpc.SsoUser;
import com.kubrick.jsbt.sso.server.common.AuthContent;

import java.io.Serializable;

/**
 * @author k
 * @version 1.0.0
 * @ClassName AuthDto
 * @description: TODO
 * @date 2021/2/7 下午5:24
 */
public class AuthDto implements Serializable {

    private static final long serialVersionUID = 4587667812642196058L;

    private AuthContent authContent;
    private SsoUser user;

    public AuthDto(AuthContent authContent, SsoUser user) {
        this.authContent = authContent;
        this.user = user;
    }

    public AuthContent getAuthContent() {
        return authContent;
    }

    public void setAuthContent(AuthContent authContent) {
        this.authContent = authContent;
    }

    public SsoUser getUser() {
        return user;
    }

    public void setUser(SsoUser user) {
        this.user = user;
    }
}
