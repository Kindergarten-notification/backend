package com.example.kindernotification.domain.post;

import com.example.kindernotification.domain.BaseTimeEntity;
import com.example.kindernotification.domain.kinder.Kinder;
import com.example.kindernotification.domain.user.User;
import com.example.kindernotification.web.dto.post.PostDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String title;

    @Lob
    private String contents;
    private String image;

    @ManyToOne(optional = false)  // 한 유치원 당 게시물 여러 개 가능
    @JoinColumn(name = "KINDER_ID")
    private Kinder kinder;

    @ManyToOne(optional = false)  // User 한 명당 게시물 여러 개 업로드 가능
    @JoinColumn(name = "USER_ID")
    private User user;

    public static Post create(Kinder kinder, User user, PostDto postDto) {
        return new Post(
                postDto.getId(),
                postDto.getTitle(),
                postDto.getContents(),
                postDto.getImage(),
                kinder,
                user
        );
    }

    public void update(PostDto postDetailDto) {
        // 객체 갱신
        if (postDetailDto.getTitle() != null)
            this.title = postDetailDto.getTitle();

        if (postDetailDto.getContents() != null)
            this.contents = postDetailDto.getContents();

        if (postDetailDto.getImage() != null)
            this.image = postDetailDto.getImage();
    }
}

