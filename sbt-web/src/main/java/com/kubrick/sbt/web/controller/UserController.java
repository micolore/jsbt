package com.kubrick.sbt.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.kubrick.sbt.web.entity.User;
import com.kubrick.sbt.web.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author k
 * @version 1.0.0
 * @ClassName UserController
 * @description: TODO
 * @date 2020/12/13 上午10:35
 */
@Controller
@Slf4j
@RequestMapping("user")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String toHome() {
        List<User> list = userService.list();
        log.info("user size:{}", list.size());
        JSONObject json = new JSONObject();

        json.put("code", 200);
        json.put("msg", "请求成功");
        return json.toJSONString();
    }

}
