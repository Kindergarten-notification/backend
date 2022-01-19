package com.example.kindernotification.domain.kinder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KinderRepository extends JpaRepository<Kinder, Long> {
    @Query
    List<Kinder> findAllDesc();
}

