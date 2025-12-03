package com.r2s.auth.dto;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String email;
    private String fullName;

//    // Getter and Setter for fullName
//    public String getFullName() {
//        return fullName;
//    }
//
//    public void setFullName(String fullName) {
//        this.fullName = fullName;
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
}

