package com.example.kindernotification.web.controller;

import com.example.kindernotification.service.post.PostService;
import com.example.kindernotification.web.dto.post.PostDetailDto;
import com.example.kindernotification.web.dto.post.PostDto;
import com.example.kindernotification.web.dto.post.PostListDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class PostApiController {

    @Autowired
    private PostService postService;

    // 게시글 목록 전체 조회
    @GetMapping("/posts")
    public ResponseEntity<List<PostListDto>> selectAllPost (@RequestParam("kinder_id") Long kinderId){
        // 서비스
        List<PostListDto> dtos = postService.selectAllPost(kinderId);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 게시글 상세 조회
    @GetMapping("/post/{id}")
    public ResponseEntity<PostDetailDto> selectDetailPost (@PathVariable("id") Long postId){
        // 서비스
        PostDetailDto dto = postService.selectDetail(postId);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    // 게시글 등록
    @PostMapping("/post")
    public ResponseEntity<PostDetailDto> createPost (@RequestParam("kinder_id") Long kinderId,
                                                     @RequestParam("user_id") Long userId,
                                                     @RequestBody PostDto postDto){
        // 서비스
        PostDetailDto dto = postService.createPost(kinderId, userId, postDto);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    // 게시글 수정
    @PatchMapping("/post/{id}")
    public ResponseEntity<PostDetailDto> updatePost (@PathVariable("id") Long postId,
                                                     @RequestParam("user_id") Long userId,
                                                     @RequestBody PostDto postDto){
        // 서비스
        PostDetailDto dtos = postService.updatePost(postId, userId, postDto);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 게시글 삭제
    @DeleteMapping("/post/{id}")
    public ResponseEntity<PostDetailDto> deletePost (@PathVariable("id") Long postId,
                                                     @RequestParam("user_id") Long userId){
        // 서비스
        PostDetailDto dtos = postService.deletePost(postId, userId);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

}
