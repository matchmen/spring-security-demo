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
 * 1.系统资源权限获取
 * 2.该类有几种实现过程
 *    a:实时获取系统权限信息，也就是demo目前使用的方式，每次请求做权限验证都从数据库获取系统权限信息，该方式的好处是能获取到最新最准确的权限信息，坏处就是频繁访问数据库
 *    b:缓存系统权限信息，系统启动时缓存起来，系统缓存或依赖第三方（redis等），每次请求做权限验证先从缓存获取权限信息，没找到再去数据库查，然后缓存起来，该方法的好处就是解决了频繁访问数据库的问题，坏处就是看拿到的权限不是最新的(当然也是有办法解决的，比如做权限更新的同时更新缓存)
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
