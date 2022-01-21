package com.example.kindernotification.web.controller;

import com.example.kindernotification.domain.post.Post;
import com.example.kindernotification.service.album.AlbumService;
import com.example.kindernotification.web.dto.AlbumDetailDto;
import com.example.kindernotification.web.dto.AlbumListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    // 앨범 목록 전체 조회
    @GetMapping("/albums")
    public ResponseEntity<List<AlbumListDto>> selectAllAlbum (@RequestParam("kinder_id") Long kinderId){
        // 서비스
        List<AlbumListDto> dtos = albumService.selectAllAlbum(kinderId);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 게시글 상세 조회
    @GetMapping("/album/{id}")
    public ResponseEntity<AlbumDetailDto> selectDetailPost (@PathVariable("id") Long postId){
        // 서비스
        AlbumDetailDto dto = albumService.selectDetailAlbum(postId);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

}
