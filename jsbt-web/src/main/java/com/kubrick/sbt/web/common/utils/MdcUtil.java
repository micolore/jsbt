package com.kubrick.sbt.web.common.utils;

/**
 * @author k
 * @version 1.0.0
 * @ClassName MdcUtil
 * @description: TODO
 * @date 2021/2/28 上午11:04
 */
public class MdcUtil {
    private static final String TRACE_ID = "TRACE_ID";

    public static String get() {
        return MDC.get();
    }

    public static void add(String value) {
        MDC.put( value);
    }

}
