package com.kubrick.sbt.web.service.impl;

import com.kubrick.sbt.web.dao.UserDao;
import com.kubrick.sbt.web.entity.UserEntity;
import com.kubrick.sbt.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author k
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	/**
	 * 保存用户
	 */
	@Override
	public void saveUser(UserEntity user) {
		userDao.insertUser(user);
	}

}
