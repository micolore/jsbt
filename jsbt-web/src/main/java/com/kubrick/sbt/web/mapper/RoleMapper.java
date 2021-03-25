package com.kubrick.sbt.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kubrick.sbt.web.domain.entity.Role;

import java.util.List;

/**
 * @author k
 * @version 1.0.0
 * @ClassName RoleMapper
 * @description: TODO
 * @date 2021/3/21 下午4:48
 */
public interface RoleMapper extends BaseMapper<Role> {

	/**
	 * 根据用户id获取用户
	 * @param id
	 * @return
	 */
	List<Role> getUserRoleByUserId(Long id);

}
