package com.example.demo.service;

import com.example.demo.enums.UserRole;
import com.example.demo.security.CustomAuthentication;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    private static final String secret = "K2e3n4d4r5i5c3k-L6a4m6a5r_19992134534534232132131?";

    public String login(String username, String password){

        List<UserRole> userRoles = List.of(UserRole.values());

        List<String> rolesString = userRoles.stream()
                .map(UserRole::name)
                .collect(Collectors.toList());


        return Jwts.builder()
                .claim("username",username)
                .claim("role", rolesString)
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }
    public Authentication authentication(String token){
        Jws<Claims> claimsJws = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build().parseSignedClaims(token);
        Claims payLoad = claimsJws.getPayload();
        String username = payLoad.get("username", String.class);
        String role = payLoad.get("role", String.class);
        return new CustomAuthentication(role,true, username);
    }
}
