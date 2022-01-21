package com.example.kindernotification.web.dto;

import com.example.kindernotification.domain.album.Album;
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

public class AlbumDetailDto {
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


    public static AlbumDetailDto create(Album album) {
        return new AlbumDetailDto(
                album.getId(),
                album.getTitle(),
                album.getContents(),
                album.getImage(),
                album.getCreatedDate(),
                album.getModifiedDate(),
                album.getUser().getName(),
                album.getUser().getNickname(),
                album.getUser().getRole().toString(),
                album.getUser().getEmail(),
                album.getUser().getKinder().getKinderName()
        );
    }
}
