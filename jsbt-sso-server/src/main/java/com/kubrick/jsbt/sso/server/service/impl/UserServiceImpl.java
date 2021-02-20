package com.kubrick.jsbt.sso.server.service.impl;

/**
 * @author k
 * @version 1.0.0
 * @ClassName UserServiceImpl
 * @description: TODO
 * @date 2021/2/19 下午8:26
 */
import java.util.ArrayList;
import java.util.List;

import com.kubrick.jsbt.sso.client.sso.client.rpc.Result;
import com.kubrick.jsbt.sso.client.sso.client.rpc.SsoUser;
import com.kubrick.jsbt.sso.server.model.User;
import com.kubrick.jsbt.sso.server.service.UserService;
import org.springframework.stereotype.Service;



@Service("userService")
public class UserServiceImpl implements UserService {

    private static List<User> userList;

    static {
        userList = new ArrayList<>();
        userList.add(new User(1, "管理员", "admin", "123456"));
    }

    @Override
    public Result<SsoUser> login(String username, String password) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                if(user.getPassword().equals(password)) {
                    return Result.createSuccess(new SsoUser(user.getId(), user.getUsername()));
                }
                else {
                    return Result.createError("密码有误");
                }
            }
        }
        return Result.createError("用户不存在");
    }
}
