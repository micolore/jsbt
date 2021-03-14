package com.kubrick.jsbt.cglib.tutorial;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author k
 * @version 1.0.0
 * @ClassName MyMethod
 * @description: TODO
 * @date 2021/3/13 下午7:12
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyMethod {

}
