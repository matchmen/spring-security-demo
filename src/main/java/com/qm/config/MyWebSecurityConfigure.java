package com.qm.config;

import com.qm.filter.MyFilterSecurityInterceptor;
import com.qm.filter.MyUsernamePasswordAuthenticationFilter;
import com.qm.suports.MyAccessDeniedHandler;
import com.qm.suports.MyAuthenticationFailureHandler;
import com.qm.suports.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * author: liqm
 * 2019-07-23
 */
@Configuration
@EnableWebSecurity
public class MyWebSecurityConfigure extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

    @Autowired
    private MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter;

    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests().anyRequest().authenticated()
                .and()
                    .formLogin()
                    .successHandler(myAuthenticationSuccessHandler)
                    .failureHandler(myAuthenticationFailureHandler)
                    .loginProcessingUrl("/user/login")
                    .permitAll()
                .and()
                    .logout()
                    .permitAll()
                .and()
                    .exceptionHandling().accessDeniedHandler(myAccessDeniedHandler)
                .and()
                    .addFilterAt(myUsernamePasswordAuthenticationFilter, MyUsernamePasswordAuthenticationFilter.class)
                    .addFilterAt(myFilterSecurityInterceptor, FilterSecurityInterceptor.class)
                .csrf().disable();

    }

}
