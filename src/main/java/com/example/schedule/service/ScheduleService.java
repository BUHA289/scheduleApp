package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.entity.User;
import com.example.schedule.repository.ScheduleRepository;
import com.example.schedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor

public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;


    public ScheduleResponseDto save(User user, String title, String contents) {


        Schedule schedule = new Schedule(user, title, contents);
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleResponseDto(savedSchedule.getId(), savedSchedule.getUser(),
                savedSchedule.getTitle(),
                savedSchedule.getContents());

    }

    public List<ScheduleResponseDto> findAll() {
        List<Schedule> schedules = scheduleRepository.findAll();
        List<ScheduleResponseDto> dtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            ScheduleResponseDto dto = new ScheduleResponseDto(schedule.getId(),
                    schedule.getUser(),
                    schedule.getTitle(), schedule.getContents());
            dtos.add(dto);

        }
        return dtos;

    }

    public ScheduleResponseDto findById(Long id) {
        Schedule findSche = scheduleRepository.findByIdOrElseThrow(id);
        return new ScheduleResponseDto(findSche.getId(), findSche.getUser(),
                findSche.getTitle(),
                 findSche.getContents());
    }

    public ScheduleResponseDto update(Long id, ScheduleRequestDto dto) {
        Schedule findSche = scheduleRepository.findByIdOrElseThrow(id);
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저입니다.")
        );
        findSche.update(user, dto.getTitle(), dto.getContents());
        // scheduleRepository.save(findSche); jpa;

        return new ScheduleResponseDto(findSche.getId(), findSche.getUser(),
                findSche.getTitle(), findSche.getContents());

    }

    public void delete(Long id) {
        if (!scheduleRepository.existsById(id)) {
            throw new IllegalArgumentException("ID없음");
        }
        scheduleRepository.deleteById(id);
    }
}
