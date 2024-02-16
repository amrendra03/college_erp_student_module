package com.server.jwt;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JwtRequestDTO {

    @NotNull(message = "Username must not be Null !!")
    @NotBlank(message = "Not Blank Username !!")
    private String username;

    @NotNull(message = "Password must not be Null !!")
    @NotBlank(message = "Not Blank Password !!")
    private String password;
}
