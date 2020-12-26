package com.kubrick.sbt.swagger.app.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author Hccake
 * @version 1.0
 * @date 2019/10/16 11:46
 */
@RequestMapping
@RestController
public class TestController {

	@ApiOperation("测试地址")
	@PostMapping("/test")
	public String test() {
		return "Hello word!";
	}

	@GetMapping("/test/{test}")
	public String test(@PathVariable String test) {
		return "Hello " + test;
	}

	@PostMapping("/formdata")
	public String test1(@RequestParam("formdata") String formdata) {
		return formdata;
	}

	@PostMapping("/xwww")
	public String test2(@RequestParam("xwww") String xwww) {
		return xwww;
	}

}
