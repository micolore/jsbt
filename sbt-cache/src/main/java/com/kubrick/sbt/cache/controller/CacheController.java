package com.kubrick.sbt.cache.controller;

import com.kubrick.sbt.cache.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
public class CacheController {

	@Autowired
	private CacheService cacheService;

	@PostMapping("/cache")
	public String test() {
		cacheService.testRedisson();
		return "Hello cache!";
	}

}
