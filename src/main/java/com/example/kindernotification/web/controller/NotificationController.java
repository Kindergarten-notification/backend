package com.example.kindernotification.web.controller;
import com.example.kindernotification.domain.notification.Notification;
import com.example.kindernotification.domain.notification.NotificationRepository;
import com.example.kindernotification.web.dto.NotificationDetailDto;
import com.example.kindernotification.web.dto.NotificationInsertDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RequestMapping("/api")
@RestController
public class NotificationController {
    @Autowired
    private NotificationRepository notificationRepository;


    // GET 전체
    @GetMapping("/notification")
    public List<Notification> index(@RequestParam("kinder_id") long kinderId) {

        return notificationRepository.findAll();
    }

    
    // GET 특정
    @GetMapping("/notification/{id}")
    public Notification show (@PathVariable Long id,
                              @RequestParam("kinder_id") long kinderId) {

        return notificationRepository.findById(id).orElse(null);
    }

    // POST
    @PostMapping("/notification")
    public Notification create(NotificationInsertDto dto) {
        Notification notification = dto.toEntity();
        return notificationRepository.save(notification);
    }
    // PATCH
    @PatchMapping("/notification/{id}")
    public ResponseEntity<Notification> update(@PathVariable Long id,
                                               @RequestBody NotificationDetailDto dto){
        // 1: 수정용 엔티티 생성
        Notification notification = dto.toEntity();
        log.info("id: {}, notification: {}", id, notification.toString());
        // 2: 대상 엔티티를 조회
        Notification target = notificationRepository.findById(id).orElse(null);
        // 3: 잘못된 요청 처리(대상이 없거나, id가 다른 경우)
        if (target == null || id != notification.getId()) {
            //400, 잘못된 요청 응답
            log.info("잘못된 요청! id: {}, notification: {}", id, notification.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 4: 업데이트 및 정상 응답(200)
        target.patch(notification);
        Notification updated = NotificationRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);

        //DELETE
        @DeleteMapping("/notification/{id}")
        public ResponseEntity<Notification> delete(@PathVariable Long id) {
            // 대상 찾기
            Notification target notificationRepository.findById(id).orElse(null);
            // 잘못된 요청 처리
            if (target == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            // 대상 삭제
            notificationRepository.delete(target);
            return ResponseEntity.status(HttpStatus.OK).build();
            // 데이터 반환

            return null;
        }
    }
}