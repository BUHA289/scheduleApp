package com.example.schedule.dto;

import com.example.schedule.entity.User;
import lombok.Getter;

@Getter
public class ScheduleRequestDto {

    private final User user;
    private final String title;
    private final String contents;


    public ScheduleRequestDto(User user, String title, String contents) {
        this.user = user;
        this.title = title;
        this.contents = contents;
    }


}
