package com.example.kindernotification.service.album;

import com.example.kindernotification.domain.album.Album;
import com.example.kindernotification.domain.album.AlbumRepository;
import com.example.kindernotification.web.dto.AlbumDetailDto;
import com.example.kindernotification.web.dto.AlbumListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    public List<AlbumListDto> selectAllAlbum(Long kinderId) {
        //조회 (Entity)
        List<Album> posts = albumRepository.findKinderAlbumSelect(kinderId);

        //변환 (Entity -> Dto)
        List<AlbumListDto> dtos = new ArrayList();

        for (int i = 0; i < posts.size(); i++) {
            Album album = posts.get(i);
            AlbumListDto dto = AlbumListDto.create(album);
            dtos.add(dto);
        }
        return dtos;
    }

    public AlbumDetailDto selectDetailAlbum(Long postId) {
        // 게시글 상세 조회
        Album target = albumRepository.findById(postId).orElseThrow(()->new IllegalArgumentException("게시판이 없습니다."));

        return AlbumDetailDto.create(target);
    }
}
