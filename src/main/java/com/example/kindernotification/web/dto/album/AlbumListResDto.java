package com.example.kindernotification.web.dto.album;

import com.example.kindernotification.domain.album.Album;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AlbumListResDto {
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
    public AlbumListResDto(Album album) {
        this.id = album.getId();
        this.title = album.getTitle();
        this.contents = album.getContents();
        this.createdDate = album.getCreatedDate();
        this.modifiedDate = album.getModifiedDate();
        this.userName = album.getUser().getName();
        this.userNickname = album.getUser().getNickname();
        this.userRole = String.valueOf(album.getUser().getRole());
        this.userEmail = album.getUser().getEmail();
        this.userKinder = album.getKinder().getKinderName();
    }
}
