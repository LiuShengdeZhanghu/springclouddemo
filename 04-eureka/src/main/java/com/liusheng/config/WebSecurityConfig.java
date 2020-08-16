package com.liusheng.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 忽略掉/eureka/**
        // 这个将要求请求携带有效的CSRF令牌，但是忽略掉/eureka/**这个路径
        http.csrf().ignoringAntMatchers("/eureka/**");
        super.configure(http);
    }
}
