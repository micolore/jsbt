package com.kubrick.jsbt.sso.client.session;

import com.kubrick.jsbt.sso.client.rpc.RpcAccessToken;
import com.kubrick.jsbt.sso.client.rpc.SsoUser;

/**
 * @author k
 * @version 1.0.0
 * @ClassName SessionAccessToken
 * @description: TODO
 * @date 2021/2/7 下午3:27
 */
public class SessionAccessToken   extends RpcAccessToken {

    private static final long serialVersionUID = 4507869346123296527L;

    /**
     * AccessToken过期时间
     */
    private long expirationTime;

    public SessionAccessToken(String accessToken, int expiresIn, String refreshToken, SsoUser user,
                              long expirationTime) {
        super(accessToken, expiresIn, refreshToken, user);
        this.expirationTime = expirationTime;
    }

    public long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(long expirationTime) {
        this.expirationTime = expirationTime;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > expirationTime;
    }
}
