package com.qm.domain;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * author: liqm
 * 2019-07-23
 */
@AllArgsConstructor
public class SysUserDetails implements UserDetails {


    private String username;

    private String password;

    private List<SysGrantedAuthority> authorityList;

    private boolean accountNonLocked;

    private boolean accountNonExpired;

    private boolean gredentialsNonExpired;

    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorityList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return gredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
