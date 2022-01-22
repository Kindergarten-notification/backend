package com.example.kindernotification.web.controller;

import com.example.kindernotification.service.album.AlbumService;
import com.example.kindernotification.web.dto.AlbumDetailDto;
import com.example.kindernotification.web.dto.AlbumDto;
import com.example.kindernotification.web.dto.AlbumListDto;
import com.example.kindernotification.web.dto.AlbumUpdateReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AlbumApiController {
    private final AlbumService albumService;

    // 앨범 목록 전체 조회
    @GetMapping("/albums")
    public ResponseEntity<List<AlbumListDto>> getAllAlbum(@RequestParam("kinderId") Long kinderId){
        // 서비스
        List<AlbumListDto> dtos = albumService.getAllAlbum(kinderId);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 앨범 상세 조회
    @GetMapping("/album/{id}")
    public ResponseEntity<AlbumDetailDto> getDetailAlbum(@PathVariable("id") Long albumId){
        // 서비스
        AlbumDetailDto dto = albumService.getDetailAlbum(albumId);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    // 앨범 등록
    @PostMapping("/album")
    public ResponseEntity<AlbumDetailDto> createAlbum (@RequestParam("kinder_id") Long kinderId,
                                                       @RequestParam("user_id") Long userId,
                                                       @RequestBody AlbumDto albumDto){
        // 서비스
        AlbumDetailDto dto = albumService.createAlbum(kinderId, userId, albumDto);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    // 앨범 수정
    @PatchMapping("/album/{id}")
    public ResponseEntity<AlbumDetailDto> updateAlbum (@PathVariable("id") Long postId,
                                                       @RequestParam("user_id") Long userId,
                                                       @RequestBody AlbumUpdateReqDto albumDto){
        // 서비스
        AlbumDetailDto dto = albumService.updateAlbum(postId, userId, albumDto);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    // 앨범 삭제
    @DeleteMapping("/album/{id}")
    public ResponseEntity<AlbumDetailDto> deleteAlbum (@PathVariable("id") Long postId,
                                                       @RequestParam("user_id") Long userId){
        // 서비스
        AlbumDetailDto dto = albumService.deleteAlbum(postId, userId);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

}
