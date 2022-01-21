package com.example.kindernotification.web.dto;

import com.example.kindernotification.domain.kinder.Kinder;
import com.example.kindernotification.domain.post.Post;
import com.example.kindernotification.domain.user.User;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PostListDto {
    private Long id;
    private String title;
    private String contents;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String kinderName;
    private String userName;
    private String userNickname;

    public static PostListDto create(Post post) {
        return new PostListDto(
                post.getId(),
                post.getTitle(),
                post.getContents(),
                post.getCreatedDate(),
                post.getModifiedDate(),
                post.getKinder().getKinderName(),
                post.getUser().getName(),
                post.getUser().getNickname()
        );
    }


}
