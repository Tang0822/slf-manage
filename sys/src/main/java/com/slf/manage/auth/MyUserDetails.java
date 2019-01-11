package com.slf.manage.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author jftang3
 */
@Getter
@Setter
public class MyUserDetails implements UserDetails {

     Integer id;

     String username;

     String password;

     Boolean enabled;

     Integer groupType;

     Integer groupId;

    Collection<? extends GrantedAuthority> authorities;

    public MyUserDetails() {
    }

    public MyUserDetails(Integer id, String username, String password, Boolean enabled, Integer groupType, Integer groupId, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.groupType = groupType;
        this.groupId = groupId;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
        return this.enabled;
    }
}
