package com.example.kindernotification.domain.post;

import com.example.kindernotification.domain.kinder.Kinder;
import com.example.kindernotification.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // 특정 유치원 게시글 모두 조회
    @Query(value = "SELECT * FROM post WHERE KINDER_ID = :kinderCode", nativeQuery = true)
    List<Post> findKinderPostSelect(Long kinderCode);

    // 특정 유치원 특정 게시글 조회
    @Query(value = "SELECT * FROM post WHERE KINDER_ID = :kinderCode AND :postId ", nativeQuery = true)
    List<Post> findKinderPostDetailSelect(Long kinderCode, int postId);
}
