package com.kubrick.sbt.web.handler;

/**
 * @author k
 * @version 1.0.0
 * @ClassName AckTransfer
 * @description: TODO
 * @date 2021/1/25 下午10:13
 */
public class AckTransfer {
    private static final int EXCEPTION_CODE = 0;

    public static GeneralResponse fail(String message, int code) {
        return new GeneralResponse(message, code);
    }

    public static GeneralResponse fail(String message) {
        return new GeneralResponse(message, EXCEPTION_CODE);
    }

}
