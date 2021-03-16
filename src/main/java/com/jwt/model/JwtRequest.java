package com.jwt.model;

import org.springframework.stereotype.Component;


public class JwtRequest {
    String username;
    String password;
    Long id;
    String email;

    public JwtRequest() {
    }



    public JwtRequest(String username, String password, Long id, String email, String rol, boolean enabled) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "JwtRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                ", email='" + email + '\'' +
                '}';
    }

}
