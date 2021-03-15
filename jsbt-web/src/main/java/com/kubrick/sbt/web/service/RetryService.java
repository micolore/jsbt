package com.kubrick.sbt.web.service;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

/**
 * @author kubrick
 */
public interface RetryService {
    /**
     * recover：指定兜底/补偿的方法名。如果不指定，默认对照 @Recover 标识的，第一入参为重试异常，其余入参和出参一致的方法；
     * interceptor：指定方法切面 bean，org.aopalliance.intercept.MethodInterceptor 实现类
     * value / include：两者用途一致，指出哪些类型需要重试；
     * exclude：和 include 相反，指出哪些异常不需要重试；
     * label：可以指定唯一标签，用于统计；
     * stateful：默认false。重试是否是有状态的；
     * maxAttempts：最大的重试次数；
     * backoff：指定 @Backoff，回退策略；
     * listeners：指定 org.springframework.retry.RetryListener 实现 bean
     */
    @Retryable(listeners = {"defaultRetryListener"}, value = {ArithmeticException.class}, maxAttempts = 2, backoff = @Backoff(delay = 10000, maxDelay = 2))
    void retryHttp();

    @Recover
    void recoverRetryHttp(ArithmeticException exception);
}
