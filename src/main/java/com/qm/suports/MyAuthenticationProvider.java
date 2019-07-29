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
 * author: liqm
 * 2019-07-26
 */
@Slf4j
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String userName = (String) authentication.getPrincipal();

        String password = (String) authentication.getCredentials();

        SysUserDetails sysUserDetails = (SysUserDetails) myUserDetailsService.loadUserByUsername(userName);

        if (Objects.isNull(sysUserDetails)) {
            throw new BadCredentialsException("用户名不存在");
        }

        if (!new BCryptPasswordEncoder().matches(password, sysUserDetails.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName, password, sysUserDetails.getAuthorities());

        log.info("欢迎登录...😁");

        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}
