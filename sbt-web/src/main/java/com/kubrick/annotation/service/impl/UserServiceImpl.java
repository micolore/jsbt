package com.kubrick.annotation.service.impl;

import com.kubrick.annotation.dao.UserDao;
import com.kubrick.annotation.entity.UserEntity;
import com.kubrick.annotation.service.UserService;
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
