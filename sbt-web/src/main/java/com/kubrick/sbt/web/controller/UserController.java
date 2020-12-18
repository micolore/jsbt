package com.kubrick.sbt.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.kubrick.sbt.web.entity.User;
import com.kubrick.sbt.web.service.OrganizationService;
import com.kubrick.sbt.web.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author k
 * @version 1.0.0
 * @ClassName UserController
 * @description: TODO
 * @date 2020/12/13 上午10:35
 */
@Controller
@Slf4j
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private OrganizationService organizationService;

	@GetMapping("/list")
	@ResponseBody
	public JSONObject toHome() {
		List<User> list = userService.list(12);
		log.info("user size:{}", list.size());
		List<Long> organizationList = organizationService.list(1);
		log.info("organizationList:{}", organizationList);

		JSONObject json = new JSONObject();
		json.put("code", 200);
		json.put("msg", "请求成功");
		return json;
	}

}
