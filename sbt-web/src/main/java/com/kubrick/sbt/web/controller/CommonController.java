package com.kubrick.sbt.web.controller;

import com.kubrick.sbt.web.annotation.MyLog;
import com.kubrick.sbt.web.entity.User;
import com.kubrick.sbt.web.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author k
 */
@RestController
@RequestMapping("common")
public class CommonController {

	@Autowired
	private UserService userService;

	@MyLog
	// @LoginRequired
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

	@Api(tags = "首页模块")
	@RestController
	public class IndexController {

		@ApiImplicitParam(name = "name", value = "姓名", required = true)
		@ApiOperation(value = "向客人问好")
		@GetMapping("/sayHi")
		public ResponseEntity<String> sayHi(@RequestParam(value = "name") String name) {
			return ResponseEntity.ok("Hi:" + name);
		}
	}

}
