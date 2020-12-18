package com.kubrick.sbt.web.service.impl;

import com.kubrick.sbt.web.cache.RedisCache;
import com.kubrick.sbt.web.dao.UserDao;
import com.kubrick.sbt.web.entity.User;
import com.kubrick.sbt.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
	public void saveUser(User user) {
		userDao.insertUser(user);
	}

	@RedisCache(key = "'userid:' + #id +':'")
	@Override
	public List<User> list(int id) {

		return userDao.list();
	}

}
