package com.kubrick.sbt.web.controller.common;

import com.kubrick.sbt.web.domain.entity.User;
import com.kubrick.sbt.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author k
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("common")
public class CommonController {

    private final UserService userService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello() {
        System.out.println("hello");
        User user = new User();
        user.setPassword("123");
        user.setUsername("k");
        userService.saveUser(user);
        return "ok";
    }

}
