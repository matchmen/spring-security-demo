package com.qm.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * author: liqm
 * 2019-07-24
 */
@Component
public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public void init() {
        this.setAuthenticationManager(authenticationManager);
    }

}
