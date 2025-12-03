package com.r2s.auth.dto;

import com.r2s.auth.entity.User;
import lombok.Data;

@Data
public class UserResponse {
    private String username;
    private String role;
    private String email;
    private String fullName;

//    // Getter and Setter for username
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    // Getter and Setter for role
//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
//
//    // Getter and Setter for email
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    // Getter and Setter for fullName
//    public String getFullName() {
//        return fullName;
//    }
//
//    public void setFullName(String fullName) {
//        this.fullName = fullName;
//    }

    public static UserResponse fromEntity(User user) {
        UserResponse res = new UserResponse();
        res.setUsername(user.getUsername());
        res.setRole(user.getRole().name());
        res.setEmail(user.getEmail());
        res.setFullName(user.getFullName());
        return res;
    }


}

