package com.musicwebbe.jwt.service;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtResponse {

    private int id;
    private String token;
    private String type = "Bearer";
    private String username;
    private String name;
    private String img;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtResponse(int id, String token, String username, String name,String img, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.token = token;
        this.username = username;
        this.name = name;
        this.img = img;
        this.authorities = authorities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
