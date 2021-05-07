package com.jwt.model;

import javax.persistence.*;

@Entity
@Table(name= "USER")
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //to increment field automatically
    private Long id;  //primary key
    private String username;
    private String password;
    private String email;


    //more properties as your project requirements

    public User() {
    }

    public User(Long id, String username, String password, String email, String rol, boolean enabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }





    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
