package com.kubrick.sbt.web.common.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author k
 * @version 1.0.0
 * @ClassName GlobalExceptionHandler
 * @description: 全局异常处理器
 * @date 2021/1/25 下午10:11
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public GeneralResponse handleMissingServletRequestParameterException(MissingServletRequestParameterException e,
                                                                         HttpServletRequest request) {
        String message = "缺失请求参数" + e.getParameterName();
        return ackTransfer(request, message, HttpStatus.BAD_REQUEST.value(), e);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public GeneralResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e,
                                                                     HttpServletRequest request) {
        String message = "请求参数" + e.getName() + "类型错误";
        return ackTransfer(request, message, HttpStatus.BAD_REQUEST.value(), e);
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    @ResponseBody
    public GeneralResponse handleHttpMessageNotReadableException(HttpMessageConversionException e,
                                                                 HttpServletRequest request) {
        String message = "参数类型错误";
        return ackTransfer(request, message, HttpStatus.BAD_REQUEST.value(), e);
    }

    /**
     * Default Exception Handler
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public GeneralResponse handleException(Exception e, HttpServletRequest request) {
        return ackTransfer(request, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e, true);
    }

    private GeneralResponse ackTransfer(HttpServletRequest request, String message, int code, Exception e,
                                        boolean printStackTrace) {
        GeneralResponse response = AckTransfer.fail(message, code);
        if (printStackTrace) {
            log.error(message, e);
        } else {
            log.error(message);
        }
        return response;
    }

    /**
     * transfer ACK
     */
    private GeneralResponse ackTransfer(HttpServletRequest request, String message, int code, Exception e) {
        return ackTransfer(request, message, code, e, false);
    }

}
