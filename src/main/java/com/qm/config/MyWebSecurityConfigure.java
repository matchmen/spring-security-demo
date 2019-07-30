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
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor; //权限过滤器

    @Autowired
    private MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter; //用户登录过滤器

    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler; //用户授权失败处理，也就是包装一下没有权限返回的信息（可有可无）

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler; //登录成功处理，也就是包装一下登录成功返回的信息（可有可无）

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler; //登录失败处理，也就是包装一下登录失败返回的信息（可有可无）

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests().anyRequest().authenticated()//所有请求都需要进行USER认证
                .and()//分割符，可有理解为xml里的结束标签
                    .formLogin()
                    .successHandler(myAuthenticationSuccessHandler)//登录成功处理设置
                    .failureHandler(myAuthenticationFailureHandler)//登录失败处理设置
                    .loginProcessingUrl("/user/login")//自定义登录请求地址，默认为/login
                    .permitAll()//设置/user/login为不需要权限验证
                .and()
                    .logout()//退出
                    .permitAll()//免于权限验证
                .and()
                    .exceptionHandling().accessDeniedHandler(myAccessDeniedHandler)//无权限处理设置
                .and()
                    .addFilterAt(myUsernamePasswordAuthenticationFilter, MyUsernamePasswordAuthenticationFilter.class)//登录验证过滤器注册
                    .addFilterAt(myFilterSecurityInterceptor, FilterSecurityInterceptor.class)//权限验证过滤器注册
                .csrf().disable();//CSRF：跨站请求伪造，关闭防跨站请求伪造攻击，默认启用

    }

}
