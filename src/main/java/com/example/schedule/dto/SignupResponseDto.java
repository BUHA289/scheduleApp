package com.example.schedule.dto;

import lombok.Getter;

@Getter
public class SignupResponseDto {
    private final Long id;
    private final String userName;
    private final String email;


    public SignupResponseDto(Long id, String userName, String email) {
        this.id = id;
        this.userName = userName;
        this.email = email;
    }


}
