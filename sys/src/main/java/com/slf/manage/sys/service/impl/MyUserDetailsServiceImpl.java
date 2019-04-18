package com.slf.manage.sys.service.impl;

import com.slf.manage.auth.MyGrantedAuthority;
import com.slf.manage.auth.MyUserDetails;
import com.slf.manage.sys.domain.Group;
import com.slf.manage.sys.domain.Permission;
import com.slf.manage.sys.domain.User;
import com.slf.manage.sys.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jftang3
 */
@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user != null) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            Group group = user.getGroup();
            List<Permission> permissions = user.getGroup().getPermissions();
            for (Permission permission : permissions) {
                GrantedAuthority grantedAuthority = new MyGrantedAuthority(group.getName(), permission.getUrl(), permission.getMethod(), permission.getIsCom(), permission.getPath());
                grantedAuthorities.add(grantedAuthority);
            }
            return new MyUserDetails(user.getId(), username, user.getPassword(), true, group.getType(), group.getId(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException(username + " do not exist!");
        }
    }
}