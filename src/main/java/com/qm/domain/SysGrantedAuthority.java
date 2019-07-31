package com.qm.domain;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/**
 * 用户权限/角色信息，用户登录成功后实例化，用于封装登录用户拥有权限/或角色的信息，以便做权限校验
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
