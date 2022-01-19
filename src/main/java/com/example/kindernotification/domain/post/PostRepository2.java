package com.example.kindernotification.domain.post;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PostRepository2 {

    @PersistenceContext
    private EntityManager em;

    // 글 등록
    public void save(Post post) {
        em.persist(post);
    }

    // 글 목록
    public List<Post> findALL() {
        return em.createQuery("SELECT p FROM Post p", Post.class)
                .getResultList();
    }

    // 글 상세 조회
    public Post findOne(Long id) {
        return em.find(Post.class, id);
    }

    // 특정 유치원의 글 조회
    public List<Post> findByKinder(Long kinder_id){
        return em.createQuery("SELECT p FROM Post p WHERE p.kinder.id = :kinder_id", Post.class)
                .setParameter("kinder_id", kinder_id)
                .getResultList();
    }

}
