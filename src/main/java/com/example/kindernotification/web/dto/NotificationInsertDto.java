package com.example.kindernotification.web.dto;

import com.example.kindernotification.domain.kinder.Kinder;
import com.example.kindernotification.domain.notification.Notification;
import com.example.kindernotification.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class NotificationInsertDto {
    private Long id;
    private String title;
    private String contents;
    private String image;
    private Kinder kinder;
    private User user;

    public static NotificationInsertDto create(Notification n) {
        return new NotificationInsertDto(
                n.getId(),
                n.getTitle(),
                n.getContents(),
                n.getImage(),
                n.getKinder(),
                n.getUser()
        );
    }
}