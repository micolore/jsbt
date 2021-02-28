package com.kubrick.sbt.web.datasource;

import java.lang.annotation.*;

/**
 * @author k
 * @version 1.0.0
 * @ClassName UsingDataSource
 * @description: TODO
 * @date 2021/2/28 下午6:33
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UsingDataSource {

    SupportDatasourceEnum type();
}

