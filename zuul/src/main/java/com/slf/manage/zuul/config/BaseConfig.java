package com.slf.manage.zuul.config;

import com.slf.manage.zuul.filter.MyFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jftang3
 */
@Configuration
public class BaseConfig {

    @Bean
    public MyFilter myFilter() {
        return new MyFilter();
    }
}
