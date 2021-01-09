package com.kubrick.sbt.web.cache;

import java.lang.annotation.*;

/**
 * @author k
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RedisCache {

	String key();

	long expire() default -1;

}
