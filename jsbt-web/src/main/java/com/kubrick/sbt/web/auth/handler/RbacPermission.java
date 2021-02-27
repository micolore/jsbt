package com.kubrick.sbt.web.auth.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.kubrick.sbt.web.domain.entity.Menu;
import com.kubrick.sbt.web.domain.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

/**
 * RBAC数据模型控制权限
 *
 * @author k
 */
@Slf4j
@Component("rbacPermission")
public class RbacPermission {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    public boolean hasPermission(HttpServletRequest request,
                                 Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        if (principal instanceof User) {
            List<Menu> menus = ((User) principal).getRoleMenus();
            log.debug("menu size:{}", menus.size());
            for (Menu menu : menus) {
                if (antPathMatcher.match(menu.getMenuUrl(), request.getRequestURI())) {
                    hasPermission = true;
                    break;
                }
            }
        }
        return hasPermission;
    }

}
