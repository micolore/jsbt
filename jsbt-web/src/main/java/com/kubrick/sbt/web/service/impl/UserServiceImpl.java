package com.kubrick.sbt.web.service.impl;

import com.kubrick.sbt.web.annotation.RedisCache;
import com.kubrick.sbt.web.dao.UserDao;
import com.kubrick.sbt.web.domain.entity.User;
import com.kubrick.sbt.web.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author k
 */
@Slf4j
@Service
@Scope("threadLocalScope")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 初始化化
     */
    @PostConstruct
    public void init() {
        log.info("UserServiceImpl init...");
    }

    /**
     * 保存用户
     */
    @Override
    public void saveUser(User user) {
        userDao.insertUser(user);
    }

    @Async
    @RedisCache(key = "'userid:' + #id +':'")
    @Override
    public List<User> list(int id) {

        return userDao.list();
    }

}
