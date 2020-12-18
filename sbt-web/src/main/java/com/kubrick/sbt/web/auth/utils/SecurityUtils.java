package com.kubrick.sbt.web.auth.utils;

import com.kubrick.sbt.web.entity.User;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author k
 * @version 1.0.0
 * @ClassName SecurityUtils
 * @description: TODO
 * @date 2020/12/13 上午11:09
 */
@UtilityClass
public class SecurityUtils {

	/**
	 * 获取Authentication
	 */
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	/**
	 * 获取系统用户Details
	 * @param authentication 令牌
	 * @return SysUser
	 * <p>
	 */
	public UserDetails getSysUserDetails(Authentication authentication) {
		if (authentication == null) {
			return null;
		}
		Object principal = authentication.getPrincipal();
		if (principal instanceof UserDetails) {
			return (UserDetails) principal;
		}
		return null;
	}

	/**
	 * 获取用户详情
	 */
	public UserDetails getSysUserDetails() {
		Authentication authentication = getAuthentication();
		return getSysUserDetails(authentication);
	}

	/**
	 * 获取系统用户
	 */
	public String getCurrentUserName() {
		UserDetails UserDetails = getSysUserDetails();
		return UserDetails == null ? null : UserDetails.getUsername();
	}

	public User getCurrentUser() {
		UserDetails userDetails = getSysUserDetails();
		return (User) userDetails;
	}

}
