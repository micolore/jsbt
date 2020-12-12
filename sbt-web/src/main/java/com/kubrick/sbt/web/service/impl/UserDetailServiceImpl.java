package com.kubrick.sbt.web.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import com.kubrick.sbt.web.dao.MenuDao;
import com.kubrick.sbt.web.dao.RoleDao;
import com.kubrick.sbt.web.dao.UserDao;
import com.kubrick.sbt.web.entity.Menu;
import com.kubrick.sbt.web.entity.Role;
import com.kubrick.sbt.web.entity.UserEntity;
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
    public UserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查找用户
        UserEntity user = userDao.getUserByUsername(username);
        System.out.println(user);
        if (user != null) {
            System.out.println("UserDetailsService");
            //根据用户id获取用户角色
            List<Role> roles = roleDao.getUserRoleByUserId(user.getId());
            // 填充权限
            Collection<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
            for (Role role : roles) {
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
            //填充权限菜单
            List<Menu> menus = menuDao.getRoleMenuByRoles(roles);
            return new UserEntity(username, user.getPassword(), authorities, menus);
        } else {
            System.out.println(username + " not found");
            throw new UsernameNotFoundException(username + " not found");
        }
    }

}
