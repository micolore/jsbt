package com.kubrick.sbt.web.service;


import com.kubrick.sbt.web.entity.User;

import java.util.List;

/**
 * @author k
 */
public interface UserService {
    /**
     * 保存用户
     *
     * @param user
     */
    void saveUser(User user);

    List<User> list(int id);
}
