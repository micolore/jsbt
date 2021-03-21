package com.kubrick.sbt.web.common.handler;

/**
 * @author k
 * @version 1.0.0
 * @ClassName GeneralResponse
 * @description: TODO
 * @date 2021/1/25 下午10:13
 */
public class GeneralResponse {
    private static final String SUCCESS = "success";
    private static final String FAILED = "failed";
    private String status;
    private Object data;
    private String message;
    private int code;

    GeneralResponse(Object data) {
        this.status = SUCCESS;
        this.data = data;
        this.message = "";
        this.code = 200;
    }

    GeneralResponse(String message, int code) {
        this.status = FAILED;
        this.data = null;
        this.message = message;
    }
}
