package com.example.schedule.repository;

import com.example.schedule.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
   // public User findByEmail(String email);

    default User findByIdOrElseThrow (Long id) {
        return findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, id + "없음")
        );
    }

    Optional<User> findUserByEmail(String email); // 옵셔널 벗길 메서드명

    default User findUserByEmailOrElseThrow(String email) {
        return findUserByEmail(email).orElseThrow( //옵셔널 벗기기
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, email +"없음")
        );
    }
}
