package com.example.kindernotification.web.dto.post;

import com.example.kindernotification.domain.kinder.Kinder;
import com.example.kindernotification.domain.user.User;
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

public class PostDto {
    private Long id;
    private String title;
    private String contents;
    private String image;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Kinder kinder;
    private User user;
}
