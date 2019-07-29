package com.qm.suports;

import com.qm.domain.SysGrantedAuthority;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 *
 * 权限验证
 * author: liqm
 * 2019-07-24
 */
@Slf4j
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {

    /**
     * @param authentication  是从spring的全局缓存SecurityContextHolder中拿到的，里面是用户的权限信息
     * @param object 被访问的URL
     * @param configAttributes  所需要的权限/角色
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {

        if (configAttributes == null || configAttributes.size() == 0) {
            return;
        }

        for(ConfigAttribute configAttribute:configAttributes){
            String needRole = configAttribute.getAttribute();
            for (GrantedAuthority grantedAuthority :authentication.getAuthorities())
                if (needRole.equalsIgnoreCase(grantedAuthority.getAuthority())) {
                    return;
                }
        }

        throw new AccessDeniedException("permission denied!");

    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
