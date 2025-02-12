package com.example.schedule.controller;

import com.example.schedule.dto.SignupRequestDto;
import com.example.schedule.dto.SignupResponseDto;
import com.example.schedule.dto.UserRequestDto;
import com.example.schedule.dto.UserResponseDto;
import com.example.schedule.repository.UserRepository;
import com.example.schedule.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signUp(@RequestBody SignupRequestDto dto) {

        SignupResponseDto signupResponseDto = userService.signUp(
                dto.getUserName(),
                dto.getEmail(),
                dto.getPassword()
        );
        return new ResponseEntity<>(signupResponseDto, HttpStatus.CREATED);

//        return new ResponseEntity<>((userService.signUp(
//                dto.getUserName(),
//                dto.getEmail(),
//                dto.getPassword())), HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll() {
           return new ResponseEntity<>((userService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        return new ResponseEntity<>((userService.findById(id)), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> update(@PathVariable Long id,
                                  @RequestBody UserRequestDto dto) {
        UserResponseDto update = userService.update(dto.getId(), dto);
        return new ResponseEntity<>(update, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}


