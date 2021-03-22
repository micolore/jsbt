package com.kubrick.sbt.web.common.auth.handler;

import com.kubrick.sbt.web.common.http.HttpConstant;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author k
 * @version 1.0.0
 * @ClassName CustomSuccessHandler
 * @description: 自定义登陆成功处理逻辑
 * @date 2021/3/21 下午10:53
 */
@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String resp = HttpConstant.respLoginOk(authentication);
        response.setContentType(HttpConstant.HTTP_HEADER_CONTENT_TYPE_JSON);
        PrintWriter out = response.getWriter();
        out.write(resp);
        out.flush();
        out.close();
    }
}
