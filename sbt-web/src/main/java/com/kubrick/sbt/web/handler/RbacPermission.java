package com.kubrick.sbt.web.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.kubrick.sbt.web.entity.Menu;
import com.kubrick.sbt.web.entity.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

/**
 * RBAC数据模型控制权限
 *
 * @author k
 */
@Component("rbacPermission")
public class RbacPermission {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        if (principal instanceof UserEntity) {
            // 读取用户所拥有的权限菜单
            List<Menu> menus = ((UserEntity) principal).getRoleMenus();
            System.out.println(menus.size());
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
