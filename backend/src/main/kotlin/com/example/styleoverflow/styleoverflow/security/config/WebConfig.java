package com.example.styleoverflow.styleoverflow.security.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class WebConfig extends WebSecurityConfigurerAdapter {
//Not in use
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v*/registration/**");
    }
}
