package com.kubrick.sbt.web.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kubrick.sbt.web.domain.NewUser;
import com.kubrick.sbt.web.domain.entity.Menu;
import com.kubrick.sbt.web.mapper.MenuMapper;
import com.kubrick.sbt.web.service.RetryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author k
 * @version 1.0.0
 * @ClassName CommonApi
 * @description: TODO
 * @date 2021/1/14 上午2:18
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common")
public class CommonApi {

    private final RetryService retryService;
    private final MenuMapper menuMapper;

    /**
     * @return
     */
    @RequestMapping(value = "/ms", method = RequestMethod.GET)
    public String ms() throws Exception {
        //retryService.retryHttp();
        //retryService.guavaRetry();

        Menu menu = menuMapper.selectById(1);
        System.out.println("menu" + menu.toString());


        return "ok";
    }

    @RequestMapping(value = "/hello_react", method = RequestMethod.GET)
    public String helloReact() throws Exception {

        List<NewUser> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            NewUser user = new NewUser();
            user.setName("kubrick");
            user.setEmail("xiaomin@sina.com");
            user.setPhone("20");
            user.setUsername("s" + i);
            list.add(user);
        }
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(list);

        return jsonString;
    }
}
