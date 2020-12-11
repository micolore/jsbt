package com.kubrick.annotation.controller;

import com.kubrick.annotation.common.MyLog;
import com.kubrick.annotation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
        userService.find();
        return "ok";
    }
}
