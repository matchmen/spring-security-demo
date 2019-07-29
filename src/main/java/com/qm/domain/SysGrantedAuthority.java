package com.qm.domain;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/**
 * author: liqm
 * 2019-07-23
 */
@AllArgsConstructor
public class SysGrantedAuthority implements GrantedAuthority {

    private String roleCode;

    @Override
    public String getAuthority() {
        return this.roleCode;
    }

}
