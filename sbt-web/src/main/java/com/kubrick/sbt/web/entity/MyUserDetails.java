package com.kubrick.sbt.web.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author k
 * @version 1.0.0
 * @ClassName MyUserDetails
 * @description: TODO
 * @date 2020/12/13 下午12:31
 */
@Data
public class MyUserDetails implements UserDetails {

	private static final long serialVersionUID = -9005214545793249372L;

	private Long id;

	private String username;

	private String password;

	private Integer createBy;

	private List<Role> userRoles;

	private List<Menu> roleMenus;

	private Integer dataScope;

	private Collection<? extends GrantedAuthority> authorities;

	public MyUserDetails(String username, String password,
			Collection<? extends GrantedAuthority> authorities, List<Menu> roleMenus,
			Integer dataScope) {
		this.username = username;
		this.password = password;
		this.authorities = authorities;
		this.roleMenus = roleMenus;
		this.dataScope = dataScope;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
