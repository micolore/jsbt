package com.kubrick.sbt.web.common.auth.handler;

import com.kubrick.sbt.web.common.http.HttpConstant;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author k
 * @version 1.0.0
 * @ClassName LogoutSuccessHandler
 * @description: TODO
 * @date 2021/3/21 下午10:52
 */
@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		String resp = HttpConstant.respLogOutOk(authentication);
		response.setContentType(HttpConstant.HTTP_HEADER_CONTENT_TYPE_JSON);
		PrintWriter out = response.getWriter();
		out.write(resp);
		out.flush();
		out.close();
	}

}
