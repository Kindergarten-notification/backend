package com.example.kindernotification.domain.kinder;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class KinderRepositoryTest {

    @Autowired
    private KinderRepository kinderRepository;

    @Test
    void findById() {
        Optional<Kinder> kinderEntity = kinderRepository.findById(3L);
        Kinder kinder = kinderEntity.get();
        System.out.println(kinder);
    }

    @Test
    void findAll() {
    }
}