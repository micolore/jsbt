package com.kubrick.jsbt.sso.client.util;

import com.kubrick.jsbt.sso.client.constant.SsoConstant;
import com.kubrick.jsbt.sso.client.rpc.RpcAccessToken;
import com.kubrick.jsbt.sso.client.rpc.SsoUser;
import com.kubrick.jsbt.sso.client.session.SessionAccessToken;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author k
 * @version 1.0.0
 * @ClassName SessionUtils
 * @description: TODO
 * @date 2021/2/7 下午3:32
 */
public class SessionUtils {
    public static SessionAccessToken getAccessToken(HttpServletRequest request) {
        return (SessionAccessToken) request.getSession().getAttribute(SsoConstant.SESSION_ACCESS_TOKEN);
    }

    public static SsoUser getUser(HttpServletRequest request) {
        return Optional.ofNullable(getAccessToken(request)).map(u -> u.getUser()).orElse(null);
    }

    public static Integer getUserId(HttpServletRequest request) {
        return Optional.ofNullable(getUser(request)).map(u -> u.getId()).orElse(null);
    }

    public static void setAccessToken(HttpServletRequest request, RpcAccessToken rpcAccessToken) {
        SessionAccessToken sessionAccessToken = null;
        if (rpcAccessToken != null) {
            sessionAccessToken = createSessionAccessToken(rpcAccessToken);
        }
        request.getSession().setAttribute(SsoConstant.SESSION_ACCESS_TOKEN, sessionAccessToken);
    }

    private static SessionAccessToken createSessionAccessToken(RpcAccessToken accessToken) {
        long expirationTime = System.currentTimeMillis() + accessToken.getExpiresIn() * 1000;
        return new SessionAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn(),
                accessToken.getRefreshToken(), accessToken.getUser(), expirationTime);
    }

    public static void invalidate(HttpServletRequest request) {
        setAccessToken(request, null);
        request.getSession().invalidate();
    }
}
