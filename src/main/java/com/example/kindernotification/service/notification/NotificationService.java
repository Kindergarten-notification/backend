package com.example.kindernotification.service.notification;

import com.example.kindernotification.domain.notification.Notification;
import com.example.kindernotification.domain.notification.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> index() {
        return notificationRepository.findAll();
    }

    public Notification show(Long id) {
        return notificationRepository.findById(id).orElse(null);
    }

    public Notification create(NotiDto dto) {
        Notification notification = dto.toEntity();
        if (notification.getId() != null) {
            return null;
        }
        return notificationRepository.save(notification);
    }

    public Notification update(Long id, NotiDto dto) {
        // 1: 수정용 엔티티 생성
        Notification notification = dto.toEntity();
        log.info("id: {}, notification: {}", id, notification.toString());
        
        // 2: 대상 엔티티를 찾기
        Notification target = notificationRepository.findById(id).orElse(null);
        
        // 3: 잘못된 요청 처리(대상이 없거나, id가 다른 경우)
        if (target == null || id != notification.getId()) {
            //400, 잘못된 요청 응답
            log.info("잘못된 요청! id: {}, notification: {}", id, notification.toString());
            return null;
        }

        // 4: 업데이트
        target.patch(notification); ///////// 추가된 코드
        Notification updated = NotificationRepository.save(target);
        return updated;
    }

    public Notification delete(Long id) {
        // 대상 찾기
        Notification target notificationRepository.findById(id).orElse(null);
        // 잘못된 요청 처리
        if (target == null) {
            return null;
        }
        // 대상 삭제
        notificationRepository.delete(target);
        return target;
    }
}
