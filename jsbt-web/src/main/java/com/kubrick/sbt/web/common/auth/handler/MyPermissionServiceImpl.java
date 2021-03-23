package com.kubrick.sbt.web.common.auth.handler;

import com.kubrick.sbt.web.common.auth.CustomerUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @author k
 * @version 1.0.0
 * @ClassName MyPermissionServiceImpl
 * @description: TODO
 * @date 2021/3/22 上午12:39
 */
@Service
public class MyPermissionServiceImpl implements MyPermissionService {

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof CustomerUserDetails) {
            CustomerUserDetails userDetails = (CustomerUserDetails) principal;
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            return authorities.contains(new SimpleGrantedAuthority(request.getRequestURI()));
        }
        return true;
    }
}
