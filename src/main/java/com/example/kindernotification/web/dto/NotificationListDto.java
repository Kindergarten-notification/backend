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
public class NotificationListDto {
    private Long id;
    private String title;
    private Kinder kinder;
    private User user;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public NotificationListDto(Notification entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.kinder = entity.getKinder();
        this.user = entity.getUser();
        this.createdDate=entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}
