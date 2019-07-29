package com.qm.suports;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * author: liqm
 * 2019-07-29
 */
@Slf4j
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        log.info("用户登录失败!原因:{}", exception.getMessage());

        response.setContentType("application/json;charset=UTF-8");

        response.getWriter().write(objectMapper.writeValueAsString("用户登录失败！...😭，" + exception.getMessage()));

    }
}
