package com.kubrick.sbt.web.common.auth.handler;

import com.kubrick.sbt.web.common.http.HttpConstant;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author k
 * @version 1.0.0
 * @ClassName CustomAuthenticationEntryPoint
 * @description: TODO
 * @date 2021/3/21 下午11:12
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String resp = HttpConstant.respNotLogin();
        response.setContentType(HttpConstant.HTTP_HEADER_CONTENT_TYPE_JSON);
        PrintWriter out = response.getWriter();
        out.write(resp);
        out.flush();
        out.close();
    }
}
