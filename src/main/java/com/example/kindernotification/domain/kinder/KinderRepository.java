package com.example.kindernotification.domain.kinder;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KinderRepository extends JpaRepository<Kinder, Long> {
    List<Kinder> findAllByOrderByIdDesc();
}

