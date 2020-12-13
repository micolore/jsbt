package com.kubrick.sbt.web.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import com.kubrick.sbt.web.dao.MenuDao;
import com.kubrick.sbt.web.dao.RoleDao;
import com.kubrick.sbt.web.dao.UserDao;
import com.kubrick.sbt.web.entity.Menu;
import com.kubrick.sbt.web.entity.Role;
import com.kubrick.sbt.web.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 获取用户相关信息
 *
 * @author k
 */
@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private MenuDao menuDao;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUserByUsername(username);
        System.out.println(user);
        if (user != null) {
            log.info("UserDetailsService current user is:{}", user.toString());
            //根据用户id获取用户角色
            List<Role> roles = roleDao.getUserRoleByUserId(user.getId());
            // 填充权限
            Collection<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
            for (Role role : roles) {
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
            //填充权限菜单
            List<Menu> menus = menuDao.getRoleMenuByRoles(roles);
            return new User(username, user.getPassword(), authorities, menus);
        } else {
            log.info("{} no found", username);
            throw new UsernameNotFoundException(username + " not found");
        }
    }

}
