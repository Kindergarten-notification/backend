package com.example.kindernotification.domain.kinder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KinderRepository extends JpaRepository<Kinder, Long> {
    @Query(value = "SELECT * FROM kinder as k ORDER BY id DESC", nativeQuery = true)
    List<Kinder> findAllOrderByIdDesc();
}

