package com.kubrick.jsbt.sso.client.filter;

/**
 * @author k
 * @version 1.0.0
 * @ClassName ParamFilter
 * @description: TODO
 * @date 2021/2/7 下午3:25
 */
public class ParamFilter {
    private String appId;
    private String appSecret;
    private String serverUrl;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }
}
