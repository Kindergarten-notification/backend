package com.example.kindernotification.domain.notification;
import com.example.kindernotification.domain.BaseTimeEntity;
import com.example.kindernotification.domain.kinder.Kinder;
import com.example.kindernotification.domain.user.User;
import com.example.kindernotification.web.dto.NotificationDto;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Entity

public class Notification extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String title;

    @Lob
    private String contents;

    private String image;

    @ManyToOne(optional = false)  // 한 유치원 당 공지 여러 개 업로드 가능
    @JoinColumn(name = "KINDER_ID")
    private Kinder kinder;

    @ManyToOne(optional = false)  // User(MANAGER) 한 명당 공지 여러 개 업로드 가능
    @JoinColumn(name = "USER_ID")
    private User user;

    public static Notification create(Kinder kinder, User user, NotificationDto notificationDto) {
        return new Notification(
                notificationDto.getId(),
                notificationDto.getTitle(),
                notificationDto.getContents(),
                notificationDto.getImage(),
                kinder,
                user
        );
    }

    public void update(NotificationDto notificationDetailDto) {
        // 객체 갱신
        if (notificationDetailDto.getTitle() != null)
            this.title = notificationDetailDto.getTitle();

        if (notificationDetailDto.getContents() != null)
            this.contents = notificationDetailDto.getContents();

        if (notificationDetailDto.getImage() != null)
            this.image = notificationDetailDto.getImage();
    }
}


