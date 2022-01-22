package com.example.kindernotification.domain.user;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 쿼리수행을 Eager조회로 authorities정보도 같이 갖고옴
    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithRoleyUsername(String username);

    User findByName(String name);

}
