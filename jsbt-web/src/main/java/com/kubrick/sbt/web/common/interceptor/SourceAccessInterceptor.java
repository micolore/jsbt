package com.kubrick.sbt.web.common.interceptor;

import com.kubrick.sbt.web.common.annotation.LoginRequired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 资源访问拦截器
 *
 * @author k
 */
public class SourceAccessInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		// 反射获取方法上的LoginRequired注解
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		LoginRequired loginRequired = handlerMethod.getMethod()
				.getAnnotation(LoginRequired.class);
		if (loginRequired == null) {
			return true;
		}
		// 有LoginRequired注解说明需要登录，提示用户登录
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print("你访问的资源需要登录");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) throws Exception {

	}

}
