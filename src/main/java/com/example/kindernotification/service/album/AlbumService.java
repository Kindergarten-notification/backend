package com.example.kindernotification.service.album;

import com.example.kindernotification.domain.album.Album;
import com.example.kindernotification.domain.album.AlbumRepository;
import com.example.kindernotification.domain.kinder.Kinder;
import com.example.kindernotification.domain.kinder.KinderRepository;
import com.example.kindernotification.domain.post.Post;
import com.example.kindernotification.domain.user.User;
import com.example.kindernotification.domain.user.UserRepository;
import com.example.kindernotification.web.dto.AlbumDetailDto;
import com.example.kindernotification.web.dto.AlbumDto;
import com.example.kindernotification.web.dto.AlbumListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KinderRepository kinderRepository;


    // 앨범 전체 조회
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

    // 앨범 상세 조회
    public AlbumDetailDto selectDetailAlbum(Long postId) {
        Album target = albumRepository.findById(postId).orElseThrow(()->new IllegalArgumentException("앨범이 없습니다."));

        return AlbumDetailDto.create(target);
    }

    // 앨범 등록
    public AlbumDetailDto createAlbum(Long kinderId, Long userId, AlbumDto albumDto) {
        // 유치원 조회
        Kinder kinder = kinderRepository.findById(kinderId).orElseThrow(()->new IllegalArgumentException("유치원이 없습니다."));

        // 유저 조회
        User user = userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("유저가 없습니다."));

        // 작성자의 유치원 소속 여부 확인
        if (kinder.getId() != user.getKinder().getId())
            throw new IllegalArgumentException("작성자의 소속이 해당유치원이 아닙니다.");

        // 게시글 2000자 글자 등록 취소
        if(albumDto.getContents().toString().length() > 2000)
            throw new IllegalArgumentException("게시글의 글자가 한도를 초과 하였습니다.");

        // 댓글 엔티티 생성
        Album album = Album.create(kinder, user, albumDto);

        // 엔티티 DB 저장
        Album createdPost = albumRepository.save(album);

        return AlbumDetailDto.create(album);
    }
}
