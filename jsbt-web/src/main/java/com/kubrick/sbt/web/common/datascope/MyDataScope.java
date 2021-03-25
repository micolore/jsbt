package com.kubrick.sbt.web.common.datascope;

import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.schema.Column;

import java.util.*;

/**
 * @author k
 * @version 1.0.0
 * @ClassName MyDataScope
 * @description: TODO
 * @date 2020/12/13 下午11:01
 */
public class MyDataScope implements DataScope {

	private final static String FILTER_FIELD = "create_by";

	final String columnId = FILTER_FIELD;

	private List<String> resources;

	private List<Long> filterData;

	public MyDataScope(List<String> resources, List<Long> filterData) {
		this.resources = resources;
		this.filterData = filterData;
	}

	@Override
	public String getResource() {

		return "order";
	}

	/**
	 * 哪些表需要进行过滤
	 * @return
	 */
	@Override
	public Collection<String> getTableNames() {
		Set<String> tableNames = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
		tableNames.addAll(resources);
		return tableNames;
	}

	/**
	 * 增加过滤的值
	 * @param tableName 表名
	 * @param tableAlias 表别名，可能为空
	 * @return
	 */
	@Override
	public Expression getExpression(String tableName, Alias tableAlias) {
		Column column = new Column(tableAlias == null ? columnId : tableAlias.getName() + "." + columnId);
		ExpressionList expressionList = new ExpressionList();
		/**
		 *
		 * 1、全部数据权限 查询全部 2、本人权限 查询自己 3、本部门权限 获取本部门所有用户的 4、本部门及以下 获取本部门以下的所有用户
		 */
		List<Expression> strOgs = new ArrayList<>();
		for (Long l : filterData) {
			strOgs.add(new StringValue(String.valueOf(l)));
		}
		expressionList.setExpressions(strOgs);
		return new InExpression(column, expressionList);
	}

}
