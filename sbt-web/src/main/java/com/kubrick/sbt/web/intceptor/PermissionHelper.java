package com.kubrick.sbt.web.intceptor;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;

import java.util.Properties;

/**
 * @author k
 * @version 1.0.0
 * @ClassName PermissionHelper
 * @description: TODO
 * @date 2020/12/8 下午12:39
 */
public class PermissionHelper implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        return null;
    }

    @Override
    public Object plugin(Object o) {
        return null;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
