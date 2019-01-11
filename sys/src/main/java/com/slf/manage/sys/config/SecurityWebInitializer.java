package com.slf.manage.sys.config;

import org.apache.catalina.util.SessionConfig;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * @author jftang3
 */
public class SecurityWebInitializer extends AbstractSecurityWebApplicationInitializer {
    public SecurityWebInitializer() {
        super(WebSecurityConfig.class, SessionConfig.class);
    }
}
