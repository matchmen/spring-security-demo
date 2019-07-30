package com.qm.suports;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * author: liqm
 * 2019-07-23
 */
@Component
@Slf4j
public class MyAuthenticationManager implements AuthenticationManager {

    @Autowired
    //真正的登录验证接口,该部分本来应该是一个list（包含：用户名密码验证、验证码验证、重复登录验证等），笔者偷懒一下，只做了用户名密码验证
    private AuthenticationProvider authenticationProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //调用AuthenticationProvider验证用户名密码
        return authenticationProvider.authenticate(authentication);
    }
}
