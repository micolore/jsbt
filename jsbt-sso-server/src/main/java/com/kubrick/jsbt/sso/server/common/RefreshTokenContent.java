package com.kubrick.jsbt.sso.server.common;

/**
 * @author k
 * @version 1.0.0
 * @ClassName RefreshTokenContent
 * @description: TODO
 * @date 2021/2/7 下午5:16
 */
public class RefreshTokenContent extends AccessTokenContent {

    private static final long serialVersionUID = -1332598459045608781L;

    private String accessToken;

    private String appId;

    public RefreshTokenContent(String tgt, boolean sendLogoutRequest, String redirectUri, String accessToken,
                               String appId) {
        super(tgt, sendLogoutRequest, redirectUri);
        this.accessToken = accessToken;
        this.appId = appId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
