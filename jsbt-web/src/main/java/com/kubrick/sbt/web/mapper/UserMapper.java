package com.kubrick.sbt.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kubrick.sbt.web.domain.entity.User;

import java.util.List;

/**
 * @author k
 * @version 1.0.0
 * @ClassName UserMapper
 * @description: TODO
 * @date 2021/3/21 下午4:48
 */
public interface UserMapper extends BaseMapper<User> {

	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	User getUserByUsername(String username);

	/**
	 * 新增用户
	 * @param user
	 */
	void insertUser(User user);

	/**
	 * list user
	 * @return
	 */
	List<User> list();

}
