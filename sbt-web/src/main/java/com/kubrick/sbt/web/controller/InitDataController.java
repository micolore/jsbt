package com.kubrick.sbt.web.controller;

import com.kubrick.sbt.web.entity.User;
import com.kubrick.sbt.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author k
 */
@Controller
public class InitDataController {

	@Autowired
	private UserService userService;

	/**
	 * 初始化用户数据
	 */
	@RequestMapping("/initUserData")
	public @ResponseBody String initUserData() {
		// 普通用户
		User user = new User();
		user.setUsername("user");
		user.setPassword(new BCryptPasswordEncoder().encode("user"));
		userService.saveUser(user);
		// 管理员
		User admin = new User();
		admin.setUsername("admin");
		admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
		userService.saveUser(admin);

		return "success";
	}

}
