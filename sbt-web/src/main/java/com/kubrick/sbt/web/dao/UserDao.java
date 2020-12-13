package com.kubrick.sbt.web.dao;


import com.kubrick.sbt.web.datascope.DataPermission;
import com.kubrick.sbt.web.entity.User;

import java.util.List;

/**
 * @author k
 */
@DataPermission(resources = "t_user")
public interface UserDao {
    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 新增用户
     *
     * @param user
     */
    void insertUser(User user);

    /**
     * list user
     *
     * @return
     */
    List<User> list();


}
