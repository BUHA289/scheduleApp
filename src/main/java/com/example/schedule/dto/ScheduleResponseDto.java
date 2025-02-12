package com.example.schedule.dto;

import com.example.schedule.entity.User;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {

    private final Long id;
    private final User user;
    private final String title;
    private final String contents;

    public ScheduleResponseDto(Long id, User user, String title, String contents) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.contents = contents;
    }
}
