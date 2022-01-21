package com.example.kindernotification.service.notification;

import com.example.kindernotification.domain.notification.Notification;
import com.example.kindernotification.domain.notification.NotificationRepository;
import com.example.kindernotification.web.dto.NotificationInsertDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class NotificationService<NotificationListDto, notification, NotificationDetaillDto> {
    @Autowired
    // 공지사항 조회
    private NotificationRepository notificationRepository;

    public List<Notification> index() {

        return notificationRepository.findAll();
    }
// 공지사항 상세조회
public NotificationDetaillDto selectDetail(Long kinderId, Long notificationId) {
    // 게시글 상세 조회
    notification target = NotificationRepository.findById(notificationId).orElseThrow(()->new IllegalArgumentException("공지사항이 없습니다."));

    return NotificationDetaillDto.selectDetail(target);
}

    // 공지사항 등록
    public Notification create(NotificationInsertDto dto) {
        Notification notification = dto.toEntity();
        if (notification.getId() != null) {
            return null;
        }
        return notificationRepository.save(notification);
    }
// 공지사항 수정
    public Notification update(Long id, NotificationDetailDto dto) {
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

    // 공지사항 삭제
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
