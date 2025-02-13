package com.example.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@AllArgsConstructor
public class LoginRequestDto {



    private String email;


    private final String password;

}