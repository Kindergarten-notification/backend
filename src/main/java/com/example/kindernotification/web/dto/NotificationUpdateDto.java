package com.example.kindernotification.web.dto;

import com.example.kindernotification.domain.kinder.Kinder;
import com.example.kindernotification.domain.notification.Notification;
import com.example.kindernotification.domain.user.User;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter
@NoArgsConstructor
public class NotificationUpdateDto {
    private Long id;
    private String title;
    private String contents;
    private String image;
    private Kinder kinder;
    private User user;
    private LocalDateTime modifiedDate;

    @Builder
    public NotificationUpdateDto(Notification entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.contents = entity.getContents();
        this.image = entity.getImage();
        this.kinder = entity.getKinder();
        this.user = entity.getUser();
        this.modifiedDate = entity.getModifiedDate();
    }
}