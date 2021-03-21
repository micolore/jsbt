package com.kubrick.sbt.web.service.impl;

import com.kubrick.sbt.web.annotation.log.ServiceLog;
import com.kubrick.sbt.web.annotation.redis.RedisCache;
import com.kubrick.sbt.web.domain.entity.User;
import com.kubrick.sbt.web.mapper.UserMapper;
import com.kubrick.sbt.web.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author k
 */
@Slf4j
@RequiredArgsConstructor
@Scope("threadLocalScope")
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

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
        userMapper.insertUser(user);
    }

    @Async
    @ServiceLog(value = "查询用户")
    @RedisCache(key = "'userid:' + #id +':'")
    @Override
    public List<User> queryAll(int id) {

        return userMapper.list();
    }

}
