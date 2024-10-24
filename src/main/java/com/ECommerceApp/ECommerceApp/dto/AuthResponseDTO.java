package com.ECommerceApp.ECommerceApp.dto;

import lombok.Data;

@Data
public class AuthResponseDTO {

    private String token;
    private String tokenType = "Bearer ";

    public AuthResponseDTO(String accessToken) {
        this.token = accessToken;
    }
}
