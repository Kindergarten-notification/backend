package com.example.kindernotification.domain.kinder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KinderRepository extends JpaRepository<Kinder, Long> {

    //메인페이지에 필요한 정보 가져오기
    @Query(value = "SELECT kinderCode, kinderName,telNo, addr FROM kinder ", nativeQuery = true)
    List<Kinder> findAll();
}
