package com.example.kindernotification.domain.album;

import com.example.kindernotification.domain.BaseTimeEntity;
import com.example.kindernotification.domain.kinder.Kinder;
import com.example.kindernotification.domain.user.User;
import com.example.kindernotification.web.dto.AlbumDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Album extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String title;

    @Lob
    private String contents;

    @Column(nullable = false)
    private String image;

    @ManyToOne(optional = false)  // 한 유치원 당 사진 여러 개 업로드 가능
    @JoinColumn(name = "KINDER_ID")
    private Kinder kinder;

    @ManyToOne(optional = false)  // User 한 명당 사진 여러 개 업로드 가능
    @JoinColumn(name = "USER_ID")
    private User user;

    @Builder
    public Album(String title, String contents, String image, Kinder kinder, User user) {
        this.title = title;
        this.contents = contents;
        this.image = image;
        this.kinder = kinder;
        this.user = user;
    }

    public static Album create(Kinder kinder, User user, AlbumDto albumDto) {
        return new Album(
                albumDto.getId(),
                albumDto.getTitle(),
                albumDto.getContents(),
                albumDto.getImage(),
                kinder,
                user
        );
    }
}
