package com.kubrick.annotation.dao;

import com.kubrick.annotation.entity.User;
import com.kubrick.annotation.intceptor.InterceptAnnotation;

public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    @InterceptAnnotation
    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}

