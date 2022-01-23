package com.example.kindernotification.web.dto;

import com.example.kindernotification.domain.notification.Notification;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)

public class NotificationDetailDto {
    private Long id;
    private String title;
    private String contents;
    private String image;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String userName;
    private String userNickname;
    private String userRole;
    private String userEmail;
    private String userKinder;


    public static NotificationDetailDto create(Notification notification) {
        return new NotificationDetailDto(
                notification.getId(),
                notification.getTitle(),
                notification.getContents(),
                notification.getImage(),
                notification.getCreatedDate(),
                notification.getModifiedDate(),
                notification.getUser().getName(),
                notification.getUser().getNickname(),
                notification.getUser().getRole().toString(),
                notification.getUser().getEmail(),
                notification.getUser().getKinder().getKinderName()
        );
    }
}
