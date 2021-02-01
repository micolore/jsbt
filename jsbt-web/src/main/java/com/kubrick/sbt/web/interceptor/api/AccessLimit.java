package com.kubrick.sbt.web.interceptor.api;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
/**
 * @author k
 * @version 1.0.0
 * @ClassName AccessLimit
 * @description: TODO
 * @date 2021/2/1 下午7:02
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface AccessLimit {

    int seconds();
    int maxCount();
    boolean needLogin()default true;
}
