package com.qm.suports;

import com.qm.domain.SysGrantedAuthority;
import com.qm.domain.SysUserDetails;
import com.qm.entity.User;
import com.qm.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * author: liqm
 * 2019-07-23
 */
@Slf4j
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if(Objects.isNull(user)){
            return null;
        }

        List<SysGrantedAuthority> authorityList = new ArrayList<>();

        SysGrantedAuthority sysAuthority = new SysGrantedAuthority(user.getRole().getRoleCode());

        authorityList.add(sysAuthority);

        UserDetails userDetails = new SysUserDetails(user.getUsername(),user.getPassword(),authorityList,true,true,true,true);

        return userDetails;
    }

}
