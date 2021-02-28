package com.kubrick.sbt.web.datasource;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author k
 * @version 1.0.0
 * @ClassName DataSourceAspect
 * @description: TODO
 * @date 2021/2/28 下午6:34
 */
@Slf4j
@Aspect
@Configuration
public class DataSourceAspect {

    public DataSourceAspect() {
        log.info("DataSourceAspect is init");
    }


    @Pointcut("@within(com.kubrick.sbt.web.datasource.UsingDataSource) || " +
            "@annotation(com.kubrick.sbt.web.datasource.UsingDataSource)")
    public void pointCut() {

    }

    @Before("pointCut() && @annotation(usingDataSource)")
    public void doBefore(UsingDataSource usingDataSource) {
        log.info("select dataSource---" + usingDataSource.type());
        DataSourceContextHolder.setDatabaseHolder(usingDataSource.type());
    }

    @After("pointCut()")
    public void doAfter() {
        DataSourceContextHolder.clear();
    }

}
