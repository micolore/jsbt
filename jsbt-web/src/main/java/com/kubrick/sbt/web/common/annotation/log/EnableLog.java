package com.kubrick.sbt.web.common.annotation.log;

import com.kubrick.sbt.web.config.LogFilterWebConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/** @author k EnableLog */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(LogFilterWebConfig.class)
public @interface EnableLog {

}
