package com.example.kindernotification.web.controller;
import com.example.kindernotification.service.notification.NotificationService;
import com.example.kindernotification.web.dto.NotificationInsertDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api")
public class NotificationController<NotificationListDto, NotificationDetailDto> {
@Autowired
    private NotificationService notificationService;


    // 조회
    @GetMapping("/notification")
    public ResponseEntity<List<NotificationListDto>> selectAllPost (@RequestParam("kinder_id") long kinderId){

        // kinder_id 받아오는지 로그 확인
        log.info("kinder_id -> " + kinderId);

        // 서비스
        List<NotificationListDto> dtos = NotificationService.selectAll(kinderId);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 상세조회
    @GetMapping("/notification/{id}")
    public ResponseEntity<NotificationDetailDto> selectDetail (@PathVariable("id") long postId,
                                                       @RequestParam("kinder_id") long kinderId){

        // id, postId 받아오는지 로그 확인
        log.info("id -> " + postId);
        log.info("kinder_id -> " + kinderId);

        // 서비스
        NotificationDetailDto dtos = notificationService.selectDetail(kinderId, Id);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);

    // 등록
    @PostMapping("/api/notification")
        public ResponseEntity<NotificationInsertDto> create (@RequestParam("kinder_id") long kinderId,
        @RequestParam("user_id") long userId,
        @RequestBody NotificationInsertDto postInsertDto){
            // id, postId 받아오는지 로그 확인
            log.info("id -> " + userId);
            log.info("kinder_id -> " + kinderId);

            // 서비스
            NotificationInsertDto dtos = notificationService.create(notificationInsertDto, kinderId, userId);

            // 결과 응답
            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        }



    // 수정
    @PatchMapping("/notification/{id}")
        public ResponseEntity<NotificationDetailDto> updateNotification (@PathVariable("id") Long Id,
                @RequestParam("kinder_id") Long kinderId,
                @RequestParam("user_id") Long userId,
                @RequestBody PostDetailDto NotificationDetailDto){
            // 서비스
            NotificationDetailDto dtos = notificationService.update(NotificationDetailDto, kinderId, Id, userId);

            // 결과 응답
            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        }
        // 삭제
        @DeleteMapping("/notification/{id}")
        public ResponseEntity<NotificationDetailDto> deletePost (@PathVariable("id") Long postId,
                @RequestParam("kinder_id") Long kinderId,
                @RequestParam("user_id") Long userId){
            // 서비스
            NotificationDetailDto dtos = notificationService.delete(Id, userId, kinderId);

            // 결과 응답
            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        }

    }

