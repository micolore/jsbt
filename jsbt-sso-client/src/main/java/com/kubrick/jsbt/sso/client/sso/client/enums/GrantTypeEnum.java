package com.kubrick.jsbt.sso.client.sso.client.enums;
/**
 * @author k
 * @version 1.0.0
 * @ClassName GrantTypeEnum
 * @description: TODO
 * @date 2021/2/7 下午3:23
 */
public enum GrantTypeEnum {
    /**
     * 授权码模式
     */
    AUTHORIZATION_CODE("authorization_code"),

    /**
     * 密码模式
     */
    PASSWORD("password");

    private String value;

    private GrantTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
