package com.kubrick.annotation.controller;

import com.kubrick.annotation.common.MyLog;
import com.kubrick.annotation.entity.User;
import com.kubrick.annotation.entity.UserEntity;
import com.kubrick.annotation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author k
 */
@RestController
@RequestMapping("common")
public class CommonController {

    @Autowired
    private UserService userService;

    @MyLog
    //@LoginRequired
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello() {
        System.out.println("hello");
        UserEntity user = new UserEntity();
        user.setPassword("123");
        user.setUsername("k");
        userService.saveUser(user);
        return "ok";
    }
}
