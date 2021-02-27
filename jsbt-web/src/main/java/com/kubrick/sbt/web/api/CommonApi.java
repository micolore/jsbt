package com.kubrick.sbt.web.api;

import com.kubrick.sbt.web.spring.SpringApplicationContextUtils;
import com.kubrick.sbt.web.spring.SpringFactoryUtils;
import com.kubrick.sbt.web.spring.SpringApplicationContextAwareUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/common")
public class CommonApi {

    @Autowired
    private SpringFactoryUtils myService;
    @Autowired
    private SpringApplicationContextAwareUtils applicationContextAwareUtils;
    @Autowired
    private SpringApplicationContextUtils springApplicationContextUtils;

    @RequestMapping(value = "/ms", method = RequestMethod.GET)
    @ResponseBody
    public String ms() {
        myService.getBean("userServiceImpl");
        applicationContextAwareUtils.getBean("userServiceImpl");
        springApplicationContextUtils.getBean("userServiceImpl");
        return "ok";
    }

}
