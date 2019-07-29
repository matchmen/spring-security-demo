package com.qm.domain;

import lombok.AllArgsConstructor;
import org.springframework.security.access.ConfigAttribute;

/**
 * author: liqm
 * 2019-07-24
 */
@AllArgsConstructor
public class SysConfigAttribute implements ConfigAttribute {

    private String roleCode;

    @Override
    public String getAttribute() {
        return this.roleCode;
    }

}
