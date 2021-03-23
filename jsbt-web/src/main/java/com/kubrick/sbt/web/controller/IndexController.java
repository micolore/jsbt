package com.kubrick.sbt.web.controller;

import com.kubrick.sbt.web.domain.entity.User;
import com.kubrick.sbt.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author k
 * @version 1.0.0
 * @ClassName IndexController
 * @description: index page
 * @date 2020/12/20 上午10:17
 */
@RequiredArgsConstructor
@RestController
public class IndexController {

    private final UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody User user, HttpServletRequest request) {
        userService.login(user.getUsername(), user.getPassword(), "", request);
        return "ok";
    }

}
