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
    public List<PostListDto> selectAllPost(Long kinderId) {

        /*//조회 (Entity)
        List<Post> postDetail = postRepository.findKinderPostDetailSelect(kinderId, postId);

        //변환 (Entity -> Dto)
        List<PostDetailDto> dtos = new ArrayList();
        PostDetailDto dto = PostDetailDto.selectDetail(postDetail.get((int) (postId - 1)));
        dtos.add(dto);*/

        return postRepository.findKinderPostSelect(kinderId)
                .stream()
                .map(post -> PostListDto.selectAll(post))
                .collect(Collectors.toList());
    }

    // 게시글 상세 조회
    public PostDetailDto selectDetail(Long kinderId, Long postId) {
        // 게시글 상세 조회
        Post target = postRepository.findById(postId).orElseThrow(()->new IllegalArgumentException("게시판이 없습니다."));

        return PostDetailDto.selectDetail(target);
    }

    // 게시글 등록
    @Transactional
    public PostInsertDto createPost(PostInsertDto postInsertDto, Long kinderId, Long userId) {
        // 유치원 조회
        Kinder kinder = kinderRepository.findById(kinderId).orElseThrow(()->new IllegalArgumentException("유치원이 없습니다."));

        // 유저 조회
        User user = userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("유저가 없습니다."));

        // 작성자의 유치원 소속 여부 확인
        if (kinder.getId() != user.getKinder().getId())
            throw new IllegalArgumentException("작성자의 소속이 해당유치원이 아닙니다.");

        // 댓글 엔티티 생성
        Post post = Post.create(postInsertDto, kinder, user);

        // 엔티티 DB 저장
        Post createdPost = postRepository.save(post);

        return PostInsertDto.create(createdPost);
    }

    // 게시글 수정
    @Transactional
    public PostDetailDto updatePost(PostDetailDto postDetailDto, Long postId, Long userId) {
        // 게시글 상세 조회
        Post target = postRepository.findById(postId).orElseThrow(()->new IllegalArgumentException("게시판이 없습니다."));

        // 유저 조회
        User user = userRepository.findById(userId).orElseThrow(()-> new IllegalArgumentException("유저가 없습니다."));

        // 게시글의 유치원과 작성자의 유치원 소속 여부 확인
        if (target.getKinder().getId() != user.getKinder().getId())
            throw new IllegalArgumentException("작성자의 소속이 해당유치원이 아닙니다.");

        // USER, MANAGER, ADMIN,
        // 조회한 유저의 권한
        String userRole = user.getRole().toString();

        // 권한 여부
        // 0 - 권한을 부여하지 않음
        // 1 - 권한을 부여함
        int sucess = 0;

        // 권한 설정
        switch (userRole){
            case "USER":
                sucess = 1;
                break;
            case "MANAGER":
                sucess = 1;
                break;
            case "ADMIN":
                sucess = 1;
                break;
            default:
                sucess = 0;
        }

        // 권한을 부여받지 못하면
        if(sucess != 1)
            throw new IllegalArgumentException("수정권한이 없습니다.");

        // 게시글 수정
        target.update(postDetailDto);

        // 엔티티 DB 저장
        Post createdPost = postRepository.save(target);

        return PostDetailDto.selectDetail(createdPost);
    }

    // 게시글 삭제
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