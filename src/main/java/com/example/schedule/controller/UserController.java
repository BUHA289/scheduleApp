package com.example.schedule.controller;

import com.example.schedule.common.Const;
import com.example.schedule.dto.*;
import com.example.schedule.entity.User;
import com.example.schedule.repository.UserRepository;
import com.example.schedule.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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

    @PostMapping("/login")
    public String login(
            @RequestBody LoginRequestDto dto,
            HttpServletRequest request
    ) {
        //
        LoginResponseDto loginResponseDto = userService.login(dto.getEmail(), dto.getPassword());
        Long userId = loginResponseDto.getId();

        // 실패시 예외처리
        if (userId == null) {
            return "session-login";
        }

        // 로그인 성공시 로직
        // Session의 Default Value는 true이다.
        // Session이 request에 존재하면 기존의 Session을 반환하고,
        // Session이 request에 없을 경우에 새로 Session을 생성한다.
        HttpSession session = request.getSession();

        // 회원 정보 조회
        UserResponseDto loginUser = userService.findById(userId);

        // Session에 로그인 회원 정보를 저장한다.
        session.setAttribute(Const.LOGIN_USER, loginUser);

        // 로그인 성공시 리다이렉트
        return "redirect:/users";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        // 로그인하지 않으면 HttpSession이 null로 반환된다.
        HttpSession session = request.getSession(false);
        // 세션이 존재하면 -> 로그인이 된 경우
        if(session != null) {
            session.invalidate(); // 해당 세션(데이터)을 삭제한다.
        }

        return "redirect:/users";
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


