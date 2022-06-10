package com.ssgassignment.productinfoapi.vo;

import lombok.Data;

@Data
public class JwtResponse {
    String message;
    String token;

    public JwtResponse(String token) {
        this.message = "Login Success.";
        this.token = token;
    }
}
