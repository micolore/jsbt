package com.kubrick.sbt.web.dao;


import com.kubrick.sbt.web.entity.UserEntity;

/**
 * @author k
 */
public interface UserDao {
	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	UserEntity getUserByUsername(String username);
	/**
	 * 新增用户
	 * @param user
	 */
	void insertUser(UserEntity user);

}
