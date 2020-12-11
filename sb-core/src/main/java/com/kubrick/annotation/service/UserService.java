package com.kubrick.annotation.service;


import com.kubrick.annotation.entity.UserEntity;

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
