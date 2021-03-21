package com.kubrick.sbt.web.common.auth;

import com.kubrick.sbt.web.domain.entity.Menu;
import com.kubrick.sbt.web.domain.entity.Role;
import com.kubrick.sbt.web.domain.entity.User;
import com.kubrick.sbt.web.mapper.MenuMapper;
import com.kubrick.sbt.web.mapper.RoleMapper;
import com.kubrick.sbt.web.mapper.UserMapper;
import com.kubrick.sbt.web.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 自定义用户认证的核心逻辑
 *
 * @author k
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final MenuMapper menuMapper;
    private final OrganizationService organizationService;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userMapper.getUserByUsername(username);

        if (user != null) {
            log.info("UserDetailsService current user is:{}", user.toString());
            // 根据用户id获取用户角色
            List<Role> roles = roleMapper.getUserRoleByUserId(user.getId());
            // 填充权限
            Collection<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
            for (Role role : roles) {
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
            // 填充权限菜单
            List<Menu> menus = menuMapper.getRoleMenuByRoles(roles);
            // 可能有多个角色，理论上应该取最大的那一个，亦或者每个人的数据权限单独进行设置
            Map map = new HashMap<String, Object>();
            map.put("ds", roles.get(0).getDataScope());
            Integer dataScope = roles.get(0).getDataScope();

            List<Long> organizationIds = organizationService.list(user.getOrganization());
            return new CustomerUserDetails(user.getUsername(), user.getPassword(), authorities, menus,
                    dataScope, organizationIds);
        } else {
            log.info("{} no found", username);
            throw new UsernameNotFoundException(username + " not found");
        }
    }

}
