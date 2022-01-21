package com.example.kindernotification.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // 유저 정보 조회
    // @Query(value = "SELECT * FROM user WHERE id = :userId", nativeQuery = true)
    // User findUserInfoDetail(Long userId);
}
