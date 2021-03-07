package com.kubrick.sbt.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.kubrick.sbt.web.domain.entity.User;
import com.kubrick.sbt.web.service.OrganizationService;
import com.kubrick.sbt.web.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

	private final UserService userService;

	private final OrganizationService organizationService;

	@GetMapping("/list")
	@ResponseBody
	public JSONObject toHome() {
		List<User> list = userService.queryAll(12);
		log.info("user size:{}", list.size());
		List<Long> organizationList = organizationService.list(1);
		log.info("organizationList:{}", organizationList);

		JSONObject json = new JSONObject();
		json.put("code", 200);
		json.put("msg", "请求成功");
		return json;
	}

}
