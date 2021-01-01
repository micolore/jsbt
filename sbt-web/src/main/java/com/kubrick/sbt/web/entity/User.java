package com.kubrick.sbt.web.entity;

import java.util.Collection;
import java.util.List;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author k
 */
@Data
public class User implements UserDetails {

	private static final long serialVersionUID = -9005214545793249372L;

	private Long id;

	private String username;

	private String password;

	private Integer organization;

	private Integer createBy;

	private List<Role> userRoles;

	private List<Menu> roleMenus;

	private Integer dataScope;

	private List<Long> organizationIds;

	private Collection<? extends GrantedAuthority> authorities;

	public User() {

	}

	public User(String username, String password,
			Collection<? extends GrantedAuthority> authorities, List<Menu> roleMenus,
			Integer dataScope, List<Long> organizationIds) {
		this.username = username;
		this.password = password;
		this.authorities = authorities;
		this.roleMenus = roleMenus;
		this.dataScope = dataScope;
		this.organizationIds = organizationIds;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
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
