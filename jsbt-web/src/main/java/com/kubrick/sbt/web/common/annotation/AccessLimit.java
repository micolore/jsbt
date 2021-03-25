package com.kubrick.sbt.web.common.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author k
 * @version 1.0.0 @ClassName AccessLimit
 * @description: 1、接口重复访问限制
 * @date 2021/2/1 下午7:02
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface AccessLimit {

	int seconds();

	int maxCount();

	boolean needLogin() default true;

}
