package com.example.kindernotification.web.controller;

import com.example.kindernotification.service.notification.NotificationService;
import com.example.kindernotification.web.dto.NotificationDetailDto;
import com.example.kindernotification.web.dto.NotificationDto;
import com.example.kindernotification.web.dto.NotificationListDto;
import lombok.extern.slf4j. Slf4j;
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
    private NotificationService notificationService;

    // 공지사항 목록 전체 조회
    @GetMapping("/notifications")
    public ResponseEntity<List<NotificationListDto>> getAllNotification(@RequestParam("kinder_id") Long kinderId){
        // 서비스
        List<NotificationListDto> dtos = notificationService.selectAllNotification(kinderId);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 공지사항 상세조회
    @GetMapping("/notification/{id}")
    public ResponseEntity<NotificationDetailDto> getDetailNotification(@PathVariable("id") Long postId){
        // 서비스
        NotificationDetailDto dto = notificationService.selectDetail(postId);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    // 공지사항 등록
    @PostMapping("/notification")
    public ResponseEntity<NotificationDetailDto> createNotification(@RequestParam("kinder_id") Long kinderId,
                                                                    @RequestParam("user_id") Long userId,
                                                                    @RequestBody NotificationDto notificationDto){
        // 서비스
        NotificationDetailDto dto = notificationService.createNotification(kinderId, userId, notificationDto);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    // 공지사항 수정
    @PatchMapping("/notification/{id}")
    public ResponseEntity<NotificationDetailDto> updateNotification(@PathVariable("id") Long postId,
                                                                    @RequestParam("user_id") Long userId,
                                                                    @RequestBody NotificationDto notificationDto){
        // 서비스
        NotificationDetailDto dtos = notificationService.updateNotification(postId, userId, notificationDto);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    //공지사항 삭제
    @DeleteMapping("/notification/{id}")
    public ResponseEntity<NotificationDetailDto> deleteNotification(@PathVariable("id") Long postId,
                                                                    @RequestParam("user_id") Long userId){
        // 서비스
        NotificationDetailDto dtos = notificationService.deleteNotification(postId, userId);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
}

