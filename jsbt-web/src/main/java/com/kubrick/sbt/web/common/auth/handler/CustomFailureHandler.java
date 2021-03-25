package com.kubrick.sbt.web.common.auth.handler;

import com.kubrick.sbt.web.common.http.HttpConstant;
import org.springframework.boot.devtools.restart.FailureHandler;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author k
 * @version 1.0.0
 * @ClassName CustomFailureHandler
 * @description: 自定义异常处理-登录失败
 * @date 2021/3/21 下午10:53
 */
@Component
public class CustomFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException ex) throws IOException, ServletException {
		String resp = HttpConstant.respUnauthorized(ex);
		response.setContentType(HttpConstant.HTTP_HEADER_CONTENT_TYPE_JSON);
		PrintWriter out = response.getWriter();
		out.write(resp);
		out.flush();
		out.close();
	}

}
