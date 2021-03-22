package com.kubrick.sbt.web.common.auth.handler;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author k
 * @version 1.0.0
 * @ClassName MyPermissionService
 * @description: TODO
 * @date 2021/3/22 上午12:38
 */
public interface MyPermissionService {

    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
