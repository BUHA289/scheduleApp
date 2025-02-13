package com.example.schedule.service;

import com.example.schedule.dto.*;
import com.example.schedule.entity.User;
import com.example.schedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;



@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;

//    public SignupResponseDto signUp(SignupRequestDto dto) {
//        User user = new User(dto.getUserName(), dto.getEmail(), dto.getPassword());
    public SignupResponseDto signUp(String userName, String email, String password) {
        User user = new User(userName, email, password);
        User savedUser = userRepository.save(user);
        return new SignupResponseDto(savedUser.getId(), savedUser.getUserName(), savedUser.getEmail());

    }

    public List<UserResponseDto> findAll() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> userDtos = new ArrayList<>();
        for (User user : users) {
            UserResponseDto userResponseDto = new UserResponseDto(
                    user.getId(),
                    user.getUserName(),
                    user.getEmail()
            );
            userDtos.add(userResponseDto);
        }
        return userDtos;
    }

    public UserResponseDto findById(Long id) {
        User user = userRepository.findByIdOrElseThrow(id);
        return new UserResponseDto(
                user.getId(),
                user.getUserName(),
                user.getEmail()
        );
    }

    public UserResponseDto update(Long id, UserRequestDto dto) {
        User findUser = userRepository.findByIdOrElseThrow(id);
        findUser.update(dto.getUserName(), dto.getEmail(), findUser.getPassword());
        return new UserResponseDto(findUser.getId(), findUser.getUserName(), findUser.getEmail());
    }

    public void deleteById(Long id) {
        User findUser = userRepository.findByIdOrElseThrow(id);
        userRepository.delete(findUser);
    }


    public LoginResponseDto login(String email, String password) {
        // 🔹 입력받은 userName과 일치하는 유저 조회
        User user = userRepository.findUserByEmailOrElseThrow(email);

        // 🔹 유저 정보 검증 (비밀번호 확인)
        if (!user.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호 일치안함");
        }

        // 🔹 로그인 성공 시 userId 반환
        // 🔹 로그인 성공 시 userId 반환
        return new LoginResponseDto(user.getId());
    }
}


