package com.example.kindernotification.web.dto.album;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AlbumPatchReqDto {
    private String title;
    private String contents;
    private String image;
}
