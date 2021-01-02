package com.kubrick.sbt.web.dao;

import com.kubrick.sbt.web.domain.entity.Role;

import java.util.List;

/**
 * @author k
 */
public interface RoleDao {

	/**
	 * 根据用户id获取用户
	 * @param id
	 * @return
	 */
	List<Role> getUserRoleByUserId(Long id);

}
