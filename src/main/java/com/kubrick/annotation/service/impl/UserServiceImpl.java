package com.kubrick.annotation.service.impl;

import com.kubrick.annotation.dao.UserDao;
import com.kubrick.annotation.entity.User;
import com.kubrick.annotation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void find() {
        User user = userDao.selectByPrimaryKey(1);
        System.out.println("user:" + user.getUserName());
    }
}
