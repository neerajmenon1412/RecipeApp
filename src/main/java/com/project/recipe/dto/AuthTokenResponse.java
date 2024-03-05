package com.project.recipe.dto;

public class AuthTokenResponse {
    private String token;

    public AuthTokenResponse(String token) {
        this.token = token;
    }

    public AuthTokenResponse(String token, Long userId, String name) {
        this.token = token;
        this.userId = userId;
        this.name = name;
    }

    public Long userId;

    public String name;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
