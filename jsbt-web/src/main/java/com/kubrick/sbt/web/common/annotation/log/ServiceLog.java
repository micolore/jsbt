package com.kubrick.sbt.web.common.annotation.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author k
 * @version 1.0.0 @ClassName ServiceLog
 * @description: 1、记录接口参数请求返回值 2、方法耗时 3、通用化
 * @date 2021/3/6 上午1:05
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceLog {

	/**
	 * log 说明 业务关键字
	 * @return
	 */
	String value() default "";

	/**
	 * 是否忽略,比如类上面加的有注解,类中某一个方法不想打印可以设置该属性为 true
	 * @return
	 */
	boolean ignore() default false;

}
