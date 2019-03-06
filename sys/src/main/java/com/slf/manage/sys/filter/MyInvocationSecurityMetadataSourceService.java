package com.slf.manage.sys.filter;

import com.slf.manage.sys.domain.Group;
import com.slf.manage.sys.domain.Permission;
import com.slf.manage.sys.repositories.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author jftang3
 */
@Component
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private AuthRepository authRepository;

    private Map<String, Collection<ConfigAttribute>> permissionMap = new HashMap<>();

    private void loadPermission() {
        List<Permission> permissions = authRepository.findAll();
        for (Permission permission : permissions) {
            String key = permission.getMethod() + "-" + permission.getUrl();
            List<Group> groups = permission.getGroups();
            for (Group group : groups) {
                ConfigAttribute ca = new SecurityConfig(group.getName());
                if (permissionMap.containsKey(key)) {
                    Collection<ConfigAttribute> value = permissionMap.get(key);
                    value.add(ca);
                    permissionMap.put(key, value);
                } else {
                    Collection<ConfigAttribute> atts = new ArrayList<>();
                    atts.add(ca);
                    permissionMap.put(key, atts);
                }
            }
        }
        /*if (permissionMap.size() == 0) {
            Collection<ConfigAttribute> unAuth = new ArrayList<>();
            unAuth.add(new SecurityConfig("unAuth"));
            permissionMap.put("unAuth", unAuth);
        }*/
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        permissionMap.clear();
        loadPermission();
        // o 是一个URL，被用户请求的url。
        String url = ((FilterInvocation) o).getRequestUrl();
        String method = ((FilterInvocation) o).getHttpRequest().getMethod();
        int firstQuestionMarkIndex = url.indexOf("?");
        if (firstQuestionMarkIndex != -1) {
            url = url.substring(0, firstQuestionMarkIndex);
        }
        url = method + "-" + url;
        for (String resURL : permissionMap.keySet()) {
            if ((resURL.split("-")[0].equals("ALL") && resURL.contains("/**")) || url.equals(resURL)) {
                return permissionMap.get(resURL);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
