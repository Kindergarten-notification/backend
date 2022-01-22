package com.example.kindernotification.domain.album;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    @Query(value = "SELECT * FROM album WHERE KINDER_ID = :kinderId ORDER BY id DESC", nativeQuery = true)
    List<Album> findKinderAlbumSelect(Long kinderId);
}
