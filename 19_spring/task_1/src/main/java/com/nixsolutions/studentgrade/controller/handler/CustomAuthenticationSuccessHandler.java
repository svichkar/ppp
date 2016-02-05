package com.nixsolutions.studentgrade.controller.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by konstantin on 2/4/2016.
 */
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {

        Collection<? extends GrantedAuthority> auths = authentication.getAuthorities();
        for (GrantedAuthority authorities : auths) {
            if (authorities.getAuthority().equals("admin")) {
                httpServletResponse.sendRedirect("admin");
            } else if (authorities.getAuthority().equals("user")) {
                httpServletResponse.sendRedirect("home");
            }
        }
    }
}
