package org.launchcode.pao.Models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 3, max = 15)
    private String name;

    @Email
    private String email;

    @NotNull
    private String pwHash;


    public User (String name, String email, String password){
        this.name = name;
        this.email = email;
        this.pwHash = encoder.encode(password);
//        this.password = password;
    }

    public User() {}


    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public String getPassword() {
//        return password;
//    }

    public void setPassword(String password) {
        this.pwHash = password;
    }

    public boolean isMatchingPassword(String password){
        return encoder.matches(password, pwHash);
    }

    }


