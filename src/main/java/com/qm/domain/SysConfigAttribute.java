package com.qm.domain;

import lombok.AllArgsConstructor;
import org.springframework.security.access.ConfigAttribute;

/**
 * 1.权限或角色信息类
 * 2.观察MyFilterInvocationSecurityMetadataSource的实现过程可以，该类的作用是封装权限信息
 *
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
