package com.example.kindernotification.web.controller;

import com.example.kindernotification.service.post.PostService;
import com.example.kindernotification.web.dto.post.PostDetailDto;
import com.example.kindernotification.web.dto.post.PostDto;
import com.example.kindernotification.web.dto.post.PostListResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class PostApiController {

    private final PostService postService;

    /** 특정 유치원의 post 전체 목록 가져오기
     *
     * return
     * - List<AlbumListResDto>
     * */
    @GetMapping("/posts")
    public ResponseEntity<List<PostListResDto>> selectAllPost (@RequestParam("kinder_id") Long kinderId){
        // 서비스
        List<PostListResDto> dtos = postService.selectAllPost(kinderId);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    /** id 로 특정 post 가져오기
     *
     * return
     *  - AlbumDetailResDto
     * */
    @GetMapping("/post/{id}")
    public ResponseEntity<PostDetailDto> selectDetailPost (@PathVariable("id") Long postId){
        // 서비스
        PostDetailDto dto = postService.selectDetail(postId);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    /** post 생성
     * - 유저(ROLE_MANAGER, ROLE_USER)가 게시물을 생성하려는 유치원 소속인지 체크
     *
     * return
     * - 등록 완료: status 201
     * - 등록 실패: status
     * */
    @Secured({"ROLE_USER", "ROLE_MANAGER", "ROLE_ADMIN"})
    @PostMapping("/post")
    public ResponseEntity<String> createPost (@RequestParam("kinder_id") Long kinderId,
                                                     @RequestParam("user_id") Long userId,
                                                     @RequestBody PostDto postDto){
        if (postService.createPost(kinderId, userId, postDto)) {

            return ResponseEntity.status(HttpStatus.CREATED).body("Completed to create a post.\n");
        }

        // 결과 응답
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create a post.\n");
    }

    /** post 수정
     * - 해당 게시물을 생성한 유저인지만 체크
     *    - 게시물 생성 시, 이미 소속 유치원인지 확인했기 때문에 체크할 필요 없음
     *
     * return
     * - 수정 완료: status 200
     * - 수정 실패: status 400
     * */
    @PatchMapping("/post/{id}")
    public ResponseEntity<String> updatePost (@PathVariable("id") Long postId,
                                                     @RequestParam("user_id") Long userId,
                                                     @RequestBody PostDto postDto){
        // 결과 응답
        if (postService.updatePost(postId, userId, postDto)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Completed to update a post.\n");
        }

        // 결과 응답
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update a post.\n");
    }

    /** post 삭제
     * - 해당 게시글을 생성한 유저인지만 체크
     *    - 게시글 생성 시, 이미 소속 유치원인지 확인했기 때문에 체크할 필요 없음
     *
     * return
     * - 수정 완료: status 200
     * - 수정 실패: status 400
     * */
    @DeleteMapping("/post/{id}")
    public ResponseEntity<String> deletePost (@PathVariable("id") Long postId,
                                                     @RequestParam("user_id") Long userId){
        // 결과 응답
        if (postService.deletePost(postId, userId)) {
            return ResponseEntity.status(HttpStatus.OK).body("Completed to delete a post.\n");
        }

        // 결과 응답
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to delete a post.\n");
    }

}
