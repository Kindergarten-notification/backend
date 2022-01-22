package com.example.kindernotification.web.dto;

import com.example.kindernotification.domain.album.Album;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)

public class AlbumUpdateReqDto {
    private String title;
    private String contents;
    private String image;


    public static AlbumUpdateReqDto create(Album album) {
        return new AlbumUpdateReqDto(
                album.getTitle(),
                album.getContents(),
                album.getImage()
        );
    }
}
