package com.example.kindernotification.domain.notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    // 특정 유치원 공지사항 모두 조회
    @Query(value = "SELECT * FROM notification WHERE KINDER_ID = :kinderId ORDER BY id DESC", nativeQuery = true)
    List<Notification> findKinderNotificationSelect(Long kinderId);

    // 특정 유치원 특정 공지사항 조회
//    @Query(value = "SELECT * FROM notification WHERE KINDER_ID = :kinderCode AND :Id ", nativeQuery = true)
//    List<Notification> findKinderNotificationDetailSelect(Long kinderCode, int Id);
}
