package com.example.kindernotification.domain.notification;

import com.example.kindernotification.domain.BaseTimeEntity;
import com.example.kindernotification.domain.kinder.Kinder;
import com.example.kindernotification.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @ManyToOne(optional = false)  // 한 유치원 당 사진 여러 개 업로드 가능
    private Kinder kinder;

    @ManyToOne(optional = false)  // User(MANAGER) 한 명당 공지 여러 개 업로드 가능
    private User user;
}
