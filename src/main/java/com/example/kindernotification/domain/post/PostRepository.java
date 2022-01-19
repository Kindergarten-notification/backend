package com.example.kindernotification.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    // 특정 유치원 게시글 모두 조회
    @Query(value = "SELECT * FROM post WHERE KINDER_ID = :kinderCode", nativeQuery = true)
    List<Post> findKinderPostSelect(Long kinderCode);

    // 특정 유치원 특정 게시글 조회
    @Query(value = "SELECT * FROM post WHERE KINDER_ID = :kinderCode AND :postId ", nativeQuery = true)
    List<Post> findKinderPostDetailSelect(Long kinderCode, int postId);

}
