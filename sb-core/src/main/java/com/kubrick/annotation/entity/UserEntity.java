package com.kubrick.annotation.entity;

import java.util.Collection;
import java.util.List;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author k
 */
@Data
public class UserEntity implements UserDetails {

    private static final long serialVersionUID = -9005214545793249372L;

    private Long id;
    private String username;
    private String password;
    private List<Role> userRoles;
    private List<Menu> roleMenus;

    private Collection<? extends GrantedAuthority> authorities;

    public UserEntity() {

    }

    public UserEntity(String username, String password, Collection<? extends GrantedAuthority> authorities,
                      List<Menu> roleMenus) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.roleMenus = roleMenus;
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
