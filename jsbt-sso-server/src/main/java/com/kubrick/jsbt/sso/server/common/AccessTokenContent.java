package com.kubrick.jsbt.sso.server.common;

/**
 * @author k
 * @version 1.0.0
 * @ClassName AccessTokenContent
 * @description: TODO
 * @date 2021/2/7 下午5:15
 */
public class AccessTokenContent  extends AuthContent {

    private static final long serialVersionUID = -1332598459045608781L;

    public AccessTokenContent(String tgt, boolean sendLogoutRequest, String redirectUri) {
        super(tgt, sendLogoutRequest, redirectUri);
    }
}
