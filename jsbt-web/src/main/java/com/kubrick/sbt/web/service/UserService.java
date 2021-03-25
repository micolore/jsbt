package com.kubrick.sbt.web.service;

import com.kubrick.sbt.web.domain.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author k
 */
public interface UserService {

	/**
	 * 保存用户
	 * @param user
	 */
	void saveUser(User user);

	List<User> queryAll(int id);

	void login(String username, String password, String code, HttpServletRequest request);

}
