package com.kubrick.sbt.web.common.datascope;

import java.util.List;

/**
 * 数据权限处理器
 *
 * @author k
 */
public interface DataPermissionHandler {

	/**
	 * 系统配置的所有的数据范围
	 * @return 数据范围集合
	 */
	List<DataScope> dataScopes();

	/**
	 * 是否忽略权限控制
	 * @return boolean true: 忽略，false: 进行权限控制
	 */
	boolean ignorePermissionControl();

}
