package com.example.kindernotification.service.post;

import com.example.kindernotification.domain.post.Post;
import com.example.kindernotification.domain.post.PostRepository;
import com.example.kindernotification.web.dto.PostDetailDto;
import com.example.kindernotification.web.dto.PostListDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PostService {

    @Autowired
    private PostRepository postRepository;

    // 게시글 리스트 조회
    public List<PostListDto> selectAll(long kinderId) {

        //조회 (Entity)
        List<Post> posts = postRepository.findKinderPostSelect(kinderId);

        //변환 (Entity -> Dto)
        List<PostListDto> dtos = new ArrayList();

        for (int i = 0; i < posts.size(); i++) {
            Post p = posts.get(i);
            PostListDto dto = PostListDto.selectAll(p);
            dtos.add(dto);
        }
        return dtos;
    }

    // 게시글 상세 조회
    public List<PostDetailDto> selectDetail(long kinderId, int postId){

        //조회 (Entity)
        List<Post> postDetail = postRepository.findKinderPostDetailSelect(kinderId, postId);

        //변환 (Entity -> Dto)
        List<PostDetailDto> dtos = new ArrayList();
        PostDetailDto dto = PostDetailDto.selectDetail(postDetail.get(postId-1));
        dtos.add(dto);

        return dtos;
    }





}