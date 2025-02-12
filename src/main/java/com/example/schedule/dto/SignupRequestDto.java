package com.example.schedule.dto;

import lombok.Getter;

@Getter
public class SignupRequestDto {

    private final String userName;
    private final String email;
    private final String password;

    public SignupRequestDto(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
}
