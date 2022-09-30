package com.eun.security;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication auth) throws IOException, ServletException {
        log.info("onAuthenticationSuccess !!");
        // 사용자 정보를 세션에 저장한다.
        HttpSession session = request.getSession();

        session.setAttribute("user", null);

        // ID를 기억할 경우 Cookie에 입력해준다.
//        if ( null != request.getParameter("login-remember-me") && "on".equals( request.getParameter("login-remember-me") ) )
//            CookieUtil.setCookie(response, "username", auth.getName());
//        else
//            CookieUtil.removeCookie(response, "username");

    }

}