package com.example.kindernotification.web.dto.post;

import com.example.kindernotification.domain.post.Post;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PostListResDto {
    private Long id;
    private String title;
    private String contents;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String userName;
    private String userNickname;
    private String userRole;
    private String userEmail;
    private String userKinder;

    @Builder
    public PostListResDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.createdDate = post.getCreatedDate();
        this.modifiedDate = post.getModifiedDate();
        this.userName = post.getUser().getName();
        this.userNickname = post.getUser().getNickname();
        this.userRole = String.valueOf(post.getUser().getRole());
        this.userEmail = post.getUser().getEmail();
        this.userKinder = post.getKinder().getKinderName();
    }
}
