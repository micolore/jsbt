package com.kubrick.sbt.log;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Maps;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * @author k
 * @version 1.0.0
 * @ClassName com.kubrick.sbt.log.WebLogAspect
 * @description: TODO
 * @date 2020/12/6 下午2:02
 */
@Aspect
@Component
@Slf4j
@Order(1)
public class WebLogAspect {

	/**
	 * 切入点
	 */
	@Pointcut("execution(public * com.kubrick.*.controller.*Controller.*(..))")
	public void log() {

	}

	/**
	 * 环绕操作
	 * @param point 切入点
	 * @return 原方法返回值
	 * @throws Throwable 异常信息
	 */
	@Around("log()")
	public Object aroundLog(ProceedingJoinPoint point) throws Throwable {

		// 开始打印请求日志
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

		// 打印请求相关参数
		long startTime = System.currentTimeMillis();
		Object result = point.proceed();
		String header = request.getHeader("User-Agent");
		UserAgent userAgent = UserAgent.parseUserAgentString(header);

		final WebLog l = WebLog.builder().threadId(Long.toString(Thread.currentThread().getId()))
				.threadName(Thread.currentThread().getName()).ip(getIp(request)).url(request.getRequestURL().toString())
				.classMethod(String.format("%s.%s", point.getSignature().getDeclaringTypeName(),
						point.getSignature().getName()))
				.httpMethod(request.getMethod()).requestParams(getNameAndValue(point)).result(result)
				.timeCost(System.currentTimeMillis() - startTime).userAgent(header)
				.browser(userAgent.getBrowser().toString()).os(userAgent.getOperatingSystem().toString()).build();

		log.info("Request Log Info : {}", JSONUtil.toJsonStr(l));

		return result;
	}

	/**
	 * 获取方法参数名和参数值
	 * @param joinPoint
	 * @return
	 */
	private Map<String, Object> getNameAndValue(ProceedingJoinPoint joinPoint) {

		final Signature signature = joinPoint.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		final String[] names = methodSignature.getParameterNames();
		final Object[] args = joinPoint.getArgs();

		if (ArrayUtil.isEmpty(names) || ArrayUtil.isEmpty(args)) {
			return Collections.emptyMap();
		}
		if (names.length != args.length) {
			log.warn("{}方法参数名和参数值数量不一致", methodSignature.getName());
			return Collections.emptyMap();
		}
		Map<String, Object> map = Maps.newHashMap();
		for (int i = 0; i < names.length; i++) {
			map.put(names[i], args[i]);
		}
		return map;
	}

	private static final String UNKNOWN = "unknown";

	/**
	 * 获取ip地址
	 */
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		String comma = ",";
		String localhost = "127.0.0.1";
		if (ip.contains(comma)) {
			ip = ip.split(",")[0];
		}
		if (localhost.equals(ip)) {
			// 获取本机真正的ip地址
			try {
				ip = InetAddress.getLocalHost().getHostAddress();
			}
			catch (UnknownHostException e) {
				log.error(e.getMessage(), e);
			}
		}
		return ip;
	}

}
