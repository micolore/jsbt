package com.kubrick.annotation.service.impl;

import com.kubrick.annotation.dao.UserDao;
import com.kubrick.annotation.entity.User;
import com.kubrick.annotation.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author k
 */
@Service
@NoArgsConstructor
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Override
    public void find() {
        User user = userDao.selectByPrimaryKey(1);
        System.out.println("user:" + user.getUserName());
    }
}
