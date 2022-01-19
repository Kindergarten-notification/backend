package com.example.kindernotification.web.dto;

import com.example.kindernotification.domain.kinder.Kinder;
import com.example.kindernotification.domain.post.Post;
import com.example.kindernotification.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class PostInsertDto {
    private Long id;
    private String title;
    private String contents;
    private String image;
    private Kinder kinder;
    private User user;


    public static PostInsertDto create(Post p) {
        return new PostInsertDto(
                p.getId(),
                p.getTitle(),
                p.getContents(),
                p.getImage(),
                p.getKinder(),
                p.getUser()
        );
    }
}