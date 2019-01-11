package com.slf.manage.sys.config;

import com.slf.manage.sys.filter.AuthenticationSuccessHandler;
import com.slf.manage.sys.filter.LogoutSuccessHandler;
import com.slf.manage.sys.filter.MyFilterSecurityInterceptor;
import com.slf.manage.sys.filter.MyUsernamePasswordAuthenticationFilter;
import com.slf.manage.sys.service.impl.MyUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 * @author jftang3
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 创建一个默认账号
     *
     * @return
     */
    @Override
    public UserDetailsService userDetailsServiceBean() {
        InMemoryUserDetailsManager memoryUserDetailsManager = new InMemoryUserDetailsManager();
        memoryUserDetailsManager.createUser(User.withUsername("admin").password("admin").build());
        return memoryUserDetailsManager;
    }

    @Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

    @Bean
    UserDetailsService myUserDetailsServiceImpl() {
        return new MyUserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AuthenticationSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler("/login/failure");
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new LogoutSuccessHandler();
    }

    @Bean
    MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter() throws Exception {
        MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter = new MyUsernamePasswordAuthenticationFilter();
        myUsernamePasswordAuthenticationFilter.setAuthenticationManager(super.authenticationManager());
        myUsernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        myUsernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
        return myUsernamePasswordAuthenticationFilter;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(myUserDetailsServiceImpl());
        return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 忽略静态文件
        web.ignoring().antMatchers("/css/**", "/img/**", "/fonts/**", "/info/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login/login_page").loginProcessingUrl("/login").permitAll()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler()).permitAll()
                .and()
                .addFilter(myUsernamePasswordAuthenticationFilter())
                .addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class)
                .csrf().disable();

    }
}
