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
    private AuthenticationProvider authenticationProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        return authenticationProvider.authenticate(authentication);
    }
}
