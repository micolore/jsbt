package com.kubrick.jsbt.sso.server.common;

/**
 * @author k
 * @version 1.0.0
 * @ClassName ExpirationPolicy
 * @description: TODO
 * @date 2021/2/7 下午5:16
 */
public interface ExpirationPolicy {
    /**
     * 每5分钟执行一次
     */
    public static final String SCHEDULED_CRON = "0 */5 * * * ?";

    /**
     * 定时清理
     */
    void verifyExpired();
}
