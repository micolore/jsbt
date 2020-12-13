package com.kubrick.sbt.web.interceptor;

import com.kubrick.sbt.web.datascope.*;
import com.kubrick.sbt.web.entity.User;
import com.kubrick.sbt.web.auth.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.*;

/**
 * 数据拦截器
 *
 * @author k
 */
@Slf4j
@Component
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class DataScopeInterceptor implements Interceptor {

    private final static String SQL_SELECT_FIELD = "select";

    /**
     * basic data scope filter field
     */

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());

        //先拦截到RoutingStatementHandler，里面有个StatementHandler类型的delegate变量，其实现类是BaseStatementHandler，然后就到BaseStatementHandler的成员变量mappedStatement
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");

        //id为执行的mapper方法的全路径名，如com.uv.dao.UserMapper.insertUser
        String id = mappedStatement.getId();

        //sql语句类型 select、delete、insert、update
        String sqlCommandType = mappedStatement.getSqlCommandType().toString();

        if (SQL_SELECT_FIELD.equals(sqlCommandType)) {

            User currentUser = SecurityUtils.getCurrentUser();
            List<Long> ogs = null;
            // 默认 2 本人权限
            Integer dsType = 2;
            if (currentUser != null) {
                ogs = currentUser.getOrganizationIds();
                dsType = currentUser.getDataScope();
                log.info("currentUser:{}", currentUser.toString());
            }
            BoundSql boundSql = statementHandler.getBoundSql();
            String sql = boundSql.getSql();
            Class<?> classType = Class.forName(mappedStatement.getId().substring(0, mappedStatement.getId().lastIndexOf(".")));
            String[] resources = new String[]{};
            DataPermission annotation = classType.getAnnotation(DataPermission.class);
            if (annotation != null) {
                resources = annotation.resources();
            }
            if (ogs != null && dsType > 1) {
                MyDataScope myDataScope = new MyDataScope(Arrays.asList(resources), ogs);
                DataPermissionHandler dataPermissionHandler = new DataPermissionHandler() {
                    @Override
                    public List<DataScope> dataScopes() {
                        List<DataScope> list = new ArrayList<>();
                        list.add(myDataScope);
                        return list;
                    }

                    @Override
                    public boolean ignorePermissionControl() {
                        return false;
                    }
                };
                log.info("DataScopeInterceptor originalSql:{}", sql);
                DataScopeSqlProcessor dataScopeSqlProcessor = new DataScopeSqlProcessor();
                String newSql = dataScopeSqlProcessor.parserSingle(sql, dataPermissionHandler.dataScopes());
                Field field = boundSql.getClass().getDeclaredField("sql");
                field.setAccessible(true);
                field.set(boundSql, newSql);
                log.info("DataScopeInterceptor newSql:{}", newSql);
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }

}



