package com.kubrick.sbt.web.dao;


import com.kubrick.sbt.web.entity.User;
import com.kubrick.sbt.web.intceptor.InterceptAnnotation;

import java.util.List;

/**
 * @author k
 */
public interface UserDao {
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
	@InterceptAnnotation
	List<User> list();


}
