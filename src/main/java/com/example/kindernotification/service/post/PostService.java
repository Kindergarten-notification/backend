package com.example.kindernotification.service.post;

import com.example.kindernotification.domain.kinder.Kinder;
import com.example.kindernotification.domain.kinder.KinderRepository;
import com.example.kindernotification.domain.post.Post;
import com.example.kindernotification.domain.post.PostRepository;
import com.example.kindernotification.domain.user.User;
import com.example.kindernotification.domain.user.UserRepository;
import com.example.kindernotification.web.dto.post.PostDetailDto;
import com.example.kindernotification.web.dto.post.PostDto;
import com.example.kindernotification.web.dto.post.PostListResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final KinderRepository kinderRepository;

    // 게시글 리스트 조회
    public List<PostListResDto> selectAllPost(Long kinderId) {
        List<Post> postList = postRepository.findKinderPostSelect(kinderId);

        //변환 (Entity -> Dto)
        List<PostListResDto> dtos = new ArrayList();
        for (Post post : postList) {
            dtos.add(PostListResDto.builder().post(post).build());
        }
        return dtos;
    }

    // 게시글 상세 조회
    public PostDetailDto selectDetail(Long postId) {
        // 게시글 상세 조회
        Post target = postRepository.findById(postId).orElseThrow(()->new NoSuchElementException("게시판이 없습니다."));

        return PostDetailDto.create(target);
    }

    // 게시글 등록
    @Transactional
    public boolean createPost(Long kinderId, Long userId, PostDto postDto) {
        // 유치원 조회
        Kinder kinder = kinderRepository.findById(kinderId).orElseThrow(()->new NoSuchElementException("유치원이 없습니다."));

        // 유저 조회
        User user = userRepository.findById(userId).orElseThrow(()->new NoSuchElementException("유저가 없습니다."));

        // 작성자의 유치원 소속 여부 확인 || 게시글 2000자 글자 등록 취소
        if (!kinder.equals(user.getKinder()) || postDto.getContents().length() > 2000)
            return false;

        // 댓글 엔티티 생성
        Post post = Post.create(kinder, user, postDto);

        // 엔티티 DB 저장
        Post createdPost = postRepository.save(post);

        return true;
    }

    // 게시글 수정
    @Transactional
    public boolean updatePost(Long postId, Long userId, PostDto postDto) {
        // 게시글 상세 조회
        Post post = postRepository.findById(postId).orElseThrow(()->new NoSuchElementException("게시판이 없습니다."));

        // 유저 조회
        User user = userRepository.findById(userId).orElseThrow(()-> new NoSuchElementException("유저가 없습니다."));

        /** 이미 게시글 생성할 때 소속 유치원인지 체크했기 때문에 본인이 생성한 게시글인지만 확인하면 된다.
         * */
        if (!post.getUser().equals(user))
            return false;

        // 게시글 수정
        post.update(postDto);

        // 엔티티 DB 저장
        Post updatedPost = postRepository.save(post);

        return true;
    }

    // 게시글 삭제
    @Transactional
    public boolean deletePost(Long postId, Long userId) {
        // 게시글 조회
        Post post = postRepository.findById(postId).orElseThrow(()->new IllegalArgumentException("게시판이 없습니다."));

        // 유저 조회
        User user = userRepository.findById(userId).orElseThrow(()-> new IllegalArgumentException("유저가 없습니다."));

        /** 이미 게시글 생성할 때 소속 유치원인지 체크했기 때문에 본인이 생성한 게시글인지만 확인하면 된다.
         * */
        if (!post.getUser().equals(user))
            return false;
//            throw new IllegalArgumentException("게시글의 유치원 소속이 작성자의 소속 유치원이 아닙니다.");

        // 게시글 삭제
        postRepository.delete(post);

        return true;
    }
}