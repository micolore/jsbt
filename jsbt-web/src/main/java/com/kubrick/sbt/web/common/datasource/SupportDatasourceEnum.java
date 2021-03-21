package com.kubrick.sbt.web.common.datasource;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author k
 * @version 1.0.0
 * @ClassName SupportDatasourceEnum
 * @description: TODO
 * @date 2021/2/28 下午6:29
 */
@AllArgsConstructor
@Getter
public enum SupportDatasourceEnum {

    DEFAULT_DB("jdbc:mysql://localhost:3306/jsbt_web?useUnicode=true&characterEncoding=utf8&useSSL=false", "root", "root", "spring-security"),

    PROD_DB("jdbc:mysql://localhost:3306/db_prod?useUnicode=true&characterEncoding=utf8&useSSL=false", "root", "root", "db_prod"),

    DEV_DB("jdbc:mysql://localhost:3306/db_dev?useUnicode=true&characterEncoding=utf8&useSSL=false", "root", "root", "db_dev"),

    PRE_DB("jdbc:mysql://localhost:3306/db_pre?useUnicode=true&characterEncoding=utf8&useSSL=false", "root", "root", "db_pre");

    String url;
    String username;
    String password;
    String databaseName;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
