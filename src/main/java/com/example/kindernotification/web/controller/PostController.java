package com.example.kindernotification.web.controller;

import com.example.kindernotification.service.post.PostService;
import com.example.kindernotification.web.dto.PostDetailDto;
import com.example.kindernotification.web.dto.PostListDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class PostController {

    @Autowired
    private PostService postService;

    // 게시글 목록 전체 조회
    @GetMapping("/posts")
    public ResponseEntity<List<PostListDto>> selectAllPost (@RequestParam("kinder_id") long kinderId){

        // kinder_id 받아오는지 로그 확인
        log.info("kinder_id -> " + kinderId);

        // 서비스
        List<PostListDto> dtos = postService.selectAll(kinderId);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 게시글 목록 상세 조회
    @GetMapping("/post/{id}")
    public ResponseEntity<List<PostDetailDto>> selectDetail (@PathVariable("id") int postId,
                                                             @RequestParam("kinder_id") long kinderId){

        // id, postId 받아오는지 로그 확인
        log.info("id -> " + postId);
        log.info("kinder_id -> " + kinderId);

        // 서비스
        List<PostDetailDto> dtos = postService.selectDetail(kinderId, postId);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
}
