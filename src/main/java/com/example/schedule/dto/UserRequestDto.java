package com.example.schedule.dto;

import lombok.Getter;

@Getter
public class UserRequestDto {

    private final Long id;
    private final String userName;
    private final String email;


    public UserRequestDto(Long id, String userName, String email) {
        this.id = id;
        this.userName = userName;
        this.email = email;
    }
}

