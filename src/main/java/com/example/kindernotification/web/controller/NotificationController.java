package com.example.kindernotification.web.controller;


import com.example.kindernotification.domain.notification.Notification;
import com.example.kindernotification.service.notification.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
public class NotificationController {
@Autowired
    private NotificationService notificationService;


    // GET
    @GetMapping("/api/notification")
     public List<Notification> index() {
        return notificationService.index();
    }

    @GetMapping("/api/notification/{id}")
    public Notification show(@PathVariable Long id) {
        return notificationService.show(id);
    }

    // POST
    @PostMapping("/api/notification")
    public ResponseEntity<Notification> create(@RequestBody NotiDto dto) {
        Notification created = notificationService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }



    // PATCH
    @PatchMapping("/notification/{id}")
    public ResponseEntity<Notification> update(@PathVariable Long id,
                                               @RequestBody NotiDto dto) {
        Notification updateed = notificationService.update(id, dto);
        return (updateed != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
        //DELETE
        @DeleteMapping("/notification/{id}")
            public ResponseEntity<Notification> delete(@PathVariable Long id) {
            Notification deleted = notificationService.delete(id);
            return (deleted != null) ?
                    ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }
    }

