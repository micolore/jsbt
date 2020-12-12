package com.kubrick.sbt.web.service;


import com.kubrick.sbt.web.entity.UserEntity;

/**
 * @author k
 */
public interface UserService {
    /**
     * 保存用户
     *
     * @param user
     */
    void saveUser(UserEntity user);

}
