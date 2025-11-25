package com.r2s.auth.dto;

import lombok.Data;

@Data
public class AuthReponse {
    private String token;

    public AuthReponse(String token) {
    }
}
