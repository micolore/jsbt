package com.kubrick.sbt.web.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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


    /**
     * @return
     */
    @RequestMapping(value = "/ms", method = RequestMethod.GET)
    public String ms() {
        return "ok";
    }

}
