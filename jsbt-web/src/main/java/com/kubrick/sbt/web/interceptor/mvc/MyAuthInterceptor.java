package com.kubrick.sbt.web.interceptor.mvc;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author k
 * @version 1.0.0
 * @ClassName MyAuthInterceptor
 * @description: 1、auth、log
 * @date 2021/2/28 上午10:42
 */
public class MyAuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String requestUrl = request.getRequestURI();
        if (checkAuth(requestUrl)) {
            return true;
        }
        return false;
    }

    private boolean checkAuth(String requestUrl) {
        return true;
    }

}
