package com.bootcamp.signuplogin.model;

public class Token {

    private String token;

    public Token(final String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
