package com.jfs.training.bean;

import javax.validation.constraints.NotEmpty;

/**
 * Represents a user registration request.
 * Used as a request DTO and includes Bean Validation rules for input validation.
 */
public class UserBean {

    private Long id;

    @NotEmpty(message = "Username is required")
    private String username;

    @NotEmpty(message = "Password is required")
    private String password;

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
}
