package com.kubrick.sbt.cache.controller;

import com.kubrick.sbt.cache.service.CacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author k
 * @version 1.0.0
 * @ClassName CacheController
 * @description: TODO
 * @date 2020/12/29 上午1:01
 */
@RequestMapping
@RestController
@RequiredArgsConstructor
public class CacheController {

    private final CacheService cacheService;

    @GetMapping("/cache")
    public String test() {
        cacheService.testRedisson();
        return "Hello cache!";
    }

}
