package com.example.schedule.controller;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor

public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(@RequestBody ScheduleRequestDto dto) {
        return new ResponseEntity<>((scheduleService.save(dto.getUser(),
                dto.getTitle(), dto.getContents())),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {
        return new ResponseEntity<>(scheduleService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id) {
//        return ResponseEntity.ok(scheduleService.findById(id));
        scheduleService.findById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> update(@PathVariable Long id,
                                      @RequestBody ScheduleRequestDto dto) {
//        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(
//                id, dto.getMemberName(), dto.getTitle(), dto.getContents()
//        );
//        return ResponseEntity.ok(scheduleResponseDto);
         return new ResponseEntity<>((scheduleService.update(id, dto)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        scheduleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
