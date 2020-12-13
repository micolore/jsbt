package com.kubrick.sbt.web.interceptor;

import com.kubrick.sbt.web.datascope.DataPermission;
import com.kubrick.sbt.web.datascope.DataPermissionHandler;
import com.kubrick.sbt.web.datascope.DataScope;
import com.kubrick.sbt.web.datascope.DataScopeSqlProcessor;
import com.kubrick.sbt.web.entity.User;
import com.kubrick.sbt.web.auth.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.schema.Column;
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
 * @author k
 */
@Slf4j
@Component
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class DataScopeInterceptor implements Interceptor {

    /**
     * basic data scope filter field
     */
    private static String FILTER_FIELD = "create_by";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        // 方法一
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());

        //先拦截到RoutingStatementHandler，里面有个StatementHandler类型的delegate变量，其实现类是BaseStatementHandler，然后就到BaseStatementHandler的成员变量mappedStatement
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");

        //id为执行的mapper方法的全路径名，如com.uv.dao.UserMapper.insertUser
        String id = mappedStatement.getId();

        //sql语句类型 select、delete、insert、update
        String sqlCommandType = mappedStatement.getSqlCommandType().toString();

        if ("SELECT".equals(sqlCommandType)) {

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
            String[] finalResources = resources;
            if (ogs != null && dsType > 1) {
                List<Long> finalOgs = ogs;
                DataScope dataScope = new DataScope() {
                    final String columnId = FILTER_FIELD;

                    @Override
                    public String getResource() {
                        return "order";
                    }

                    /**
                     * 哪些表需要进行过滤
                     *
                     * @return
                     */
                    @Override
                    public Collection<String> getTableNames() {
                        Set<String> tableNames = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
                        tableNames.addAll(Arrays.asList(finalResources));
                        return tableNames;
                    }

                    /**
                     * 增加过滤的值
                     *
                     * @param tableName  表名
                     * @param tableAlias 表别名，可能为空
                     * @return
                     */
                    @Override
                    public Expression getExpression(String tableName, Alias tableAlias) {
                        Column column = new Column(tableAlias == null ? columnId : tableAlias.getName() + "." + columnId);
                        ExpressionList expressionList = new ExpressionList();
                        /**
                         *
                         * 1、全部数据权限 查询全部
                         * 2、本人权限 查询自己
                         * 3、本部门权限 获取本部门所有用户的
                         * 4、本部门及以下
                         *    获取本部门以下的所有用户
                         */
                        List<Expression> strOgs = new ArrayList<>();
                        for (Long l : finalOgs) {
                            strOgs.add(new StringValue(String.valueOf(l)));
                        }
                        expressionList.setExpressions(strOgs);
                        return new InExpression(column, expressionList);
                    }
                };

                DataPermissionHandler dataPermissionHandler = new DataPermissionHandler() {
                    @Override
                    public List<DataScope> dataScopes() {
                        List<DataScope> list = new ArrayList<>();
                        list.add(dataScope);
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

                //通过反射修改sql语句
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



