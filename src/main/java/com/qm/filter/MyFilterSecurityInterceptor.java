package com.qm.filter;

import com.qm.suports.MyAccessDecisionManager;
import com.qm.suports.MyFilterInvocationSecurityMetadataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.stereotype.Service;



/**
 * 资源权限过滤器
 * author: liqm
 * 2019-07-23
 */
@Service
public class MyFilterSecurityInterceptor extends FilterSecurityInterceptor {


    @Autowired
    private MyFilterInvocationSecurityMetadataSource myFilterInvocationSecurityMetadataSource;

    @Autowired
    private MyAccessDecisionManager myAccessDecisionManager;

    @Autowired
    private void init() {
        setSecurityMetadataSource(myFilterInvocationSecurityMetadataSource);
        setAccessDecisionManager(myAccessDecisionManager);
    }

}
