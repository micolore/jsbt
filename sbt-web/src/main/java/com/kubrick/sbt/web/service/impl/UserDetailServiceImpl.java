package com.kubrick.sbt.web.service.impl;

import java.util.*;

import com.kubrick.sbt.web.dao.MenuDao;
import com.kubrick.sbt.web.dao.RoleDao;
import com.kubrick.sbt.web.dao.UserDao;
import com.kubrick.sbt.web.domain.entity.Menu;
import com.kubrick.sbt.web.domain.entity.Role;
import com.kubrick.sbt.web.domain.entity.User;
import com.kubrick.sbt.web.service.OrganizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
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

	@Autowired
	private OrganizationService organizationService;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userDao.getUserByUsername(username);

		if (user != null) {
			log.info("UserDetailsService current user is:{}", user.toString());
			// 根据用户id获取用户角色
			List<Role> roles = roleDao.getUserRoleByUserId(user.getId());
			// 填充权限
			Collection<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
			for (Role role : roles) {
				authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
			}
			// 填充权限菜单
			List<Menu> menus = menuDao.getRoleMenuByRoles(roles);
			// 可能有多个角色，理论上应该取最大的那一个，亦或者每个人的数据权限单独进行设置
			Map map = new HashMap<String, Object>();
			map.put("ds", roles.get(0).getDataScope());
			Integer dataScope = roles.get(0).getDataScope();

			List<Long> organizationIds = organizationService.list(user.getOrganization());
			return new User(user.getUsername(), user.getPassword(), authorities, menus,
					dataScope, organizationIds);
		}
		else {
			log.info("{} no found", username);
			throw new UsernameNotFoundException(username + " not found");
		}
	}

}
