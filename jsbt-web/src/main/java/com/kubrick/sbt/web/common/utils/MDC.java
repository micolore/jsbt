package com.kubrick.sbt.web.common.utils;

/**
 * @author k
 * @version 1.0.0
 * @ClassName MDC
 * @description: TODO
 * @date 2021/2/28 上午11:05
 */
public class MDC {

    private static final ThreadLocal THREAD_LOCAL = new ThreadLocal();

    public static String get() {
        return (String) THREAD_LOCAL.get();
    }

    public static void put(String traceId) {
        THREAD_LOCAL.set(traceId);
    }
}
