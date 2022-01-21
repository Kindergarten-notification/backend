package com.example.kindernotification.web.controller;

import com.example.kindernotification.service.post.PostService;
import com.example.kindernotification.web.dto.PostDetailDto;
import com.example.kindernotification.web.dto.PostInsertDto;
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
        // 서비스
        List<PostListDto> dtos = postService.selectAllPost(kinderId);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 게시글 상세 조회
    @GetMapping("/post/{id}")
    public ResponseEntity<PostDetailDto> selectDetail (@PathVariable("id") long postId,
                                                       @RequestParam("kinder_id") long kinderId){
        // 서비스
        PostDetailDto dtos = postService.selectDetail(kinderId, postId);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 게시글 등록
    @PostMapping("/post")
    public ResponseEntity<PostInsertDto> createPost (@RequestParam("kinder_id") long kinderId,
                                                     @RequestParam("user_id") long userId,
                                                     @RequestBody PostInsertDto postInsertDto){
        // 서비스
        PostInsertDto dtos = postService.createPost(postInsertDto, kinderId, userId);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 게시글 수정
    @PatchMapping("/post/{id}")
    public ResponseEntity<PostDetailDto> updatePost (@PathVariable("id") Long postId,
                                                     @RequestParam("kinder_id") Long kinderId,
                                                     @RequestParam("user_id") Long userId,
                                                     @RequestBody PostDetailDto postDetailDto){
        // 서비스
        PostDetailDto dtos = postService.updatePost(postDetailDto, kinderId, postId, userId);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 게시글 삭제
    @DeleteMapping("/post/{id}")
    public ResponseEntity<PostDetailDto> deletePost (@PathVariable("id") Long postId,
                                                     @RequestParam("kinder_id") Long kinderId,
                                                     @RequestParam("user_id") Long userId){
        // 서비스
        PostDetailDto dtos = postService.deletePost(postId, userId, kinderId);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

}
