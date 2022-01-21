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
public class NotificationListDto {
    private Long id;
    private String title;
    private String contents;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Kinder kinder;
    private User user;

    public static NotificationListDto selectAll(Notification n) {
        return new NotificationListDto(
                n.getId(),
                n.getTitle(),
                n.getContents(),
                n.getCreatedDate(),
                n.getModifiedDate(),
                n.getKinder(),
                n.getUser()
        );
    }
}

