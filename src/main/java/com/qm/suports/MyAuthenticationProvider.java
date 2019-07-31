package com.qm.suports;

import com.qm.domain.SysUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 登录验证
 * author: liqm
 * 2019-07-26
 */
@Slf4j
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private MyUserDetailsService myUserDetailsService;//UserDetailsService实现类，用于获取根据用户名获取用户信息和权限。

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String userName = (String) authentication.getPrincipal();

        String password = (String) authentication.getCredentials();
        //获取用户信息
        SysUserDetails sysUserDetails = (SysUserDetails) myUserDetailsService.loadUserByUsername(userName);

        if (Objects.isNull(sysUserDetails)) {
            throw new BadCredentialsException("用户名不存在");
        }
        //密码验证，注意加密
        if (!new BCryptPasswordEncoder().matches(password, sysUserDetails.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }
        //登录成功，将用户名、密码、权限信息包装为UsernamePasswordAuthenticationToken返回

        /**
         * 这个理需要了解一下这三个类之间的关系
         * Authentication:用户封装当前用户登录的信息
         * SecurityContext:用于登录验证通过后缓存Authentication，比如：下面返回的token将会被保存到SecurityContext。
         * SecurityContextHolder:工具类，用于获取SecurityContext,比如：在开发过程中，我想获取当前用户的用户名可以通过String username = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();拿到
         * */
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName, password, sysUserDetails.getAuthorities());

        log.info("欢迎登录...😁");

        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}
