package com.example.kindernotification.domain.notification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    // 특정 유치원 공지사항 모두 조회
    @Query(value = "SELECT * FROM notification WHERE KINDER_ID = :kinderCode", nativeQuery = true)
    List<Notification> findKinderNotificationSelect(Long kinderCode);

    // 특정 유치원 특정 공지사항 조회
    @Query(value = "SELECT * FROM notification WHERE KINDER_ID = :kinderCode AND :Id ", nativeQuery = true)
    List<Notification> findKinderNotificationDetailSelect(Long kinderCode, int Id);
}
