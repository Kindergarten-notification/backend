package com.example.kindernotification.web.dto;

import com.example.kindernotification.domain.kinder.Kinder;
import com.example.kindernotification.domain.notification.Notification;
import com.example.kindernotification.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class NotificationDetailDto {
    private Long id;
    private String title;
    private String contents;
    private String image;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Kinder kinder;
    private User user;

    public static NotificationDetailDto selectDetail(Notification n) {
        return new NotificationDetailDto(
                n.getId(),
                n.getTitle(),
                n.getContents(),
                n.getImage(),
                n.getCreatedDate(),
                n.getModifiedDate(),
                n.getKinder(),
                n.getUser()
        );
    }
}