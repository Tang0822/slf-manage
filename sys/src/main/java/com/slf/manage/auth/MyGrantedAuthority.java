package com.slf.manage.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * @author jftang3
 */
@Getter
@Setter
public class MyGrantedAuthority implements GrantedAuthority {

    private static final long serialVersionUID = 8927953484660803724L;

    private String group;
    private String url;
    private String method;
    private Integer isCommon;
    private String path;


    public MyGrantedAuthority(String group, String url, String method, Integer isCommon, String path) {
        this.group = group;
        this.url = url;
        this.method = method;
        this.isCommon = isCommon;
        this.path = path;
    }

    @Override
    public String getAuthority() {
        return this.url + ";" + this.method + ";" + this.isCommon + ";" + this.path;
    }
}
