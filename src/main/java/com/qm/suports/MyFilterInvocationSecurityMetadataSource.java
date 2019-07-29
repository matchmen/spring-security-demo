package com.qm.suports;

import com.qm.domain.SysConfigAttribute;
import com.qm.entity.Permission;
import com.qm.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * author: liqm
 * 2019-07-24
 */
@Component
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private PermissionRepository permissionRepository;

    //存储全部的所有资源（URL）与权限或角色的关系
    private Map<String, Collection<ConfigAttribute>> collectionMap = new HashMap<>();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

        Permission permission = permissionRepository.findByUrl(((FilterInvocation) object).getRequestUrl());

        Collection<ConfigAttribute> configAttributes = new ArrayList<>();

        if(Objects.isNull(permission)){
            return configAttributes;
        }
        permission.getRoles().stream().forEach(role -> {

            ConfigAttribute configAttribute = new SysConfigAttribute(role.getRoleCode());

            configAttributes.add(configAttribute);

        });

        return configAttributes;
    }

    // 该方法用于一次性加载所有资源与权限对应关系，然后缓存起来，这样不用每次都去查数据库，不是必须实现的。
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
