package com.example.kindernotification.web.dto;

import com.example.kindernotification.domain.kinder.Kinder;
import com.example.kindernotification.domain.post.Post;
import com.example.kindernotification.domain.user.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class PostListDto {
    private Long id;
    private String title;
    private String contents;

    @JsonProperty("create_date")
    private LocalDateTime createdDate;

    @JsonProperty("modifie_dates")
    private LocalDateTime modifiedDate;

    private Kinder kinder;
    private User user;

    public static PostListDto selectAll(Post p) {
        return new PostListDto(
                p.getId(),
                p.getTitle(),
                p.getContents(),
                p.getCreatedDate(),
                p.getModifiedDate(),
                p.getKinder(),
                p.getUser()
        );
    }


}
