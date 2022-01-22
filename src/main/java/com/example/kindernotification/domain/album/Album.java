package com.example.kindernotification.domain.album;

import com.example.kindernotification.domain.BaseTimeEntity;
import com.example.kindernotification.domain.kinder.Kinder;
import com.example.kindernotification.domain.user.User;
import com.example.kindernotification.web.dto.album.AlbumPostReqDto;
import com.example.kindernotification.web.dto.album.AlbumPatchReqDto;
import com.mysql.cj.x.protobuf.MysqlxCrud;
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
    public Album(User user, AlbumPostReqDto albumPostReqDto) {
        this.title = albumPostReqDto.getTitle();
        this.contents = albumPostReqDto.getContents();
        this.image = albumPostReqDto.getImage();
        this.kinder = user.getKinder();
        this.user = user;
    }

    public void update(AlbumPatchReqDto albumDto) {
        // 객체 갱신
        if (albumDto.getTitle() != null)
            this.title = albumDto.getTitle();

        if (albumDto.getContents() != null)
            this.contents = albumDto.getContents();

        if (albumDto.getImage() != null)
            this.image = albumDto.getImage();
    }
}
