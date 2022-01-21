package com.example.kindernotification.domain.kinder;

import org.springframework.data.jpa.repository.JpaRepository;

public interface KinderRepository extends JpaRepository<Kinder, Long> {
    // 유치원 정보 조회
    // @Query(value = "SELECT * FROM kinder WHERE id = :kinderId", nativeQuery = true)
    // Kinder findKinderInfoDetail(Long kinderId);
}
