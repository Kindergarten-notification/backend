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
public class NotificationListDto {
    private Long id;
    private String title;
    private String contents;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String kinderName;
    private String userName;
    private String userNickname;

    public static NotificationListDto create(Notification notification) {
        return new NotificationListDto(
                notification.getId(),
                notification.getTitle(),
                notification.getContents(),
                notification.getCreatedDate(),
                notification.getModifiedDate(),
                notification.getKinder().getKinderName(),
                notification.getUser().getName(),
                notification.getUser().getNickname()
        );
    }
}

