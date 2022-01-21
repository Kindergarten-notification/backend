package com.example.kindernotification.web.dto;

import com.example.kindernotification.domain.album.Album;
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
public class AlbumListDto {
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

    public static AlbumListDto create(Album album) {
        return new AlbumListDto(
                album.getId(),
                album.getTitle(),
                album.getContents(),
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
