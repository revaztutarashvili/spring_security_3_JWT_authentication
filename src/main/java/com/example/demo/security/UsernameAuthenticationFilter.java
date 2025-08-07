package com.example.demo.security;

import com.example.demo.enums.UserRole;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Qualifier("UsernameAuthenticationFilter")
public class UsernameAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object isAuthenticated = session.getAttribute("isAuthenticated");
        if (isAuthenticated==null){
            filterChain.doFilter(request, response);
        } else {
            Boolean authenticated = (Boolean) isAuthenticated;
            if (authenticated) {
                String username = (String) session.getAttribute("username");
                String roleStr = (String) session.getAttribute("role");
                UserRole role = UserRole.valueOf(roleStr);
                CustomAuthentication authentication = new CustomAuthentication(username, true, role);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
            }else {
                filterChain.doFilter(request, response);
            }
        }
    }
}
