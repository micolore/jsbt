package com.kubrick.sbt.swagger.constant;

import java.util.Arrays;
import java.util.List;

public final class SwaggerConstants {

	/**
	 * 默认的排除路径，排除Spring Boot默认的错误处理路径和端点
	 */
	public static final List<String> DEFAULT_EXCLUDE_PATH = Arrays.asList("/error",
			"/actuator/**");

	/**
	 * 默认扫描路径
	 */
	public static final String BASE_PATH = "/**";

	/**
	 * 默认的文档提供路径
	 */
	public static final String API_URI = "/v2/api-docs";

	private SwaggerConstants() {
	}

}
