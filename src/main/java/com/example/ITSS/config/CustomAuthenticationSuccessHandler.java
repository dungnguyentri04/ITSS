package com.example.ITSS.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println(authentication.getAuthorities());
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            response.sendRedirect("/defaultAdmin");
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("STUDENT"))) {
            response.sendRedirect("/defaultStudent");
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("TEACHER"))) {
            response.sendRedirect("/defaultTeacher");
        } else {
            // Chuyển hướng mặc định nếu không có vai trò phù hợp
            response.sendRedirect("/error");
        }
    }
}
