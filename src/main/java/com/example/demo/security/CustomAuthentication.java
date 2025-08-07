package com.example.demo.security;

import com.example.demo.enums.UserRole;
import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
import java.util.List;


public class CustomAuthentication implements Authentication {

    private final String userName;

    private boolean authenticated;

    private final UserRole role;
    @Getter
    private String firstName;


    public CustomAuthentication(String userName, boolean isAuthenticated, UserRole role) {
        this.userName = userName;
        this.authenticated = true;
        this.role=role;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(role);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return userName;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;

    }

    @Override
    public String getName() {
        return userName;
    }
}
