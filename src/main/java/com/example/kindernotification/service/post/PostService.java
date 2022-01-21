package com.example.kindernotification.service.post;

import com.example.kindernotification.domain.kinder.Kinder;
import com.example.kindernotification.domain.kinder.KinderRepository;
import com.example.kindernotification.domain.post.Post;
import com.example.kindernotification.domain.post.PostRepository;
import com.example.kindernotification.domain.user.User;
import com.example.kindernotification.domain.user.UserRepository;
import com.example.kindernotification.web.dto.PostDetailDto;
import com.example.kindernotification.web.dto.PostInsertDto;
import com.example.kindernotification.web.dto.PostListDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;


@Service
@Slf4j
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KinderRepository kinderRepository;

    // 게시글 리스트 조회
    public List<PostListDto> selectAll(Long kinderId) {

        /*
        //조회 (Entity)
        List<Post> posts = postRepository.findKinderPostSelect(kinderId);

        //변환 (Entity -> Dto)
        List<PostListDto> dtos = new ArrayList();

        for (int i = 0; i < posts.size(); i++) {
            Post p = posts.get(i);
            PostListDto dto = PostListDto.selectAll(p);
            dtos.add(dto);
        }
        */

        return postRepository.findKinderPostSelect(kinderId)
                .stream()
                .map(post -> PostListDto.selectAll(post))
                .collect(Collectors.toList());
    }

    // 게시글 상세 조회
    public List<PostDetailDto> selectDetail(Long kinderId, Long postId) {

        //조회 (Entity)
        List<Post> postDetail = postRepository.findKinderPostDetailSelect(kinderId, postId);

        //변환 (Entity -> Dto)
        List<PostDetailDto> dtos = new ArrayList();
        PostDetailDto dto = PostDetailDto.selectDetail(postDetail.get((int) (postId - 1)));
        dtos.add(dto);

        return dtos;
    }

    public PostInsertDto create(PostInsertDto postInsertDto, Long kinderId, Long userId) {
        // 유치원 조회
        // Kinder kinder = kinderRepository.findKinderInfoDetail(kinderId);
        Kinder kinder = kinderRepository.findById(kinderId).orElseThrow(()->new IllegalArgumentException("유치원이 없습니다."));

        // 유저 조회
        // User user = userRepository.findUserInfoDetail(userId);
        User user = userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("유저가 없습니다."));

        // 댓글 엔티티 생성
        Post post = Post.create(postInsertDto, kinder, user);

        // 엔티티 DB 저장
        Post createdPost = postRepository.save(post);

        return PostInsertDto.create(createdPost);
    }

    @Transactional
    public PostDetailDto updatePost(PostDetailDto postDetailDto, Long kinderId, Long postId, Long userId) {
        // 게시글 상세 조회
        Post target = postRepository.findById(postId).orElseThrow(()->new IllegalArgumentException("게시판이 없습니다."));

        // 유치원 조회
        //Kinder kinder = kinderRepository.findKinderInfoDetail(kinderId);

        // 유저 조회
        //User user = userRepository.findUserInfoDetail(userId);

        // 게시글 수정
        target.update(postDetailDto);

        // 엔티티 DB 저장
        Post createdPost = postRepository.save(target);

        return PostDetailDto.selectDetail(createdPost);
    }

    @Transactional
    public PostDetailDto deletePost(Long postId, Long userId, Long kinderId) {
        // 게시글 조회
        Post target = postRepository.findById(postId).orElseThrow(()->new IllegalArgumentException("게시판이 없습니다."));

        // 조회된 게시글 DB에서 삭제
        postRepository.delete(target);

        // DTO 변환
        return PostDetailDto.selectDetail(target);
    }
}