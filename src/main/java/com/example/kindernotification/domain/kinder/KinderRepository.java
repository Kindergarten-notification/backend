package com.example.kindernotification.domain.kinder;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KinderRepository extends JpaRepository<Kinder, Long> {

    @Override
    Optional<Kinder> findById(Long id);

    @Override
    List<Kinder> findAll();
}
