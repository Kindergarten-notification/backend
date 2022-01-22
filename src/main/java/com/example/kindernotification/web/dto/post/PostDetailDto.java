package com.example.kindernotification.web.dto.post;

import com.example.kindernotification.domain.post.Post;
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

public class PostDetailDto {
    private Long id;
    private String title;
    private String contents;
    private String image;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String userName;
    private String userNickname;
    private String userRole;
    private String userEmail;
    private String userKinder;


    public static PostDetailDto create(Post post) {
        return new PostDetailDto(
                post.getId(),
                post.getTitle(),
                post.getContents(),
                post.getImage(),
                post.getCreatedDate(),
                post.getModifiedDate(),
                post.getUser().getName(),
                post.getUser().getNickname(),
                post.getUser().getRole().toString(),
                post.getUser().getEmail(),
                post.getUser().getKinder().getKinderName()
        );
    }
}
