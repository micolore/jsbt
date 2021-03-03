package com.kubrick.sbt.web.api;

import com.kubrick.sbt.web.service.UserService;
import com.kubrick.sbt.web.spring.SpringApplicationContextUtils;
import com.kubrick.sbt.web.spring.SpringFactoryUtils;
import com.kubrick.sbt.web.spring.SpringApplicationContextAwareUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

    private final SpringFactoryUtils myService;
    private final SpringApplicationContextAwareUtils applicationContextAwareUtils;
    private final SpringApplicationContextUtils springApplicationContextUtils;

    /**
     *
     * @return
     */
    @RequestMapping(value = "/test_spring_context", method = RequestMethod.GET)
    @ResponseBody
    public String ms() {
        UserService userServiceImpl1 = (UserService) myService.getBean("userServiceImpl");
        userServiceImpl1.list(99);
        UserService userServiceImpl2 = (UserService) applicationContextAwareUtils.getBean("userServiceImpl");
        userServiceImpl2.list(101);
        UserService userServiceImpl3 = (UserService) springApplicationContextUtils.getBean("userServiceImpl");
        userServiceImpl3.list(102);
        return "ok";
    }

}
