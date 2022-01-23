package com.example.kindernotification.service.album;

import com.example.kindernotification.domain.album.Album;
import com.example.kindernotification.domain.album.AlbumRepository;
import com.example.kindernotification.domain.user.User;
import com.example.kindernotification.domain.user.UserRepository;
import com.example.kindernotification.web.dto.album.AlbumDetailResDto;
import com.example.kindernotification.web.dto.album.AlbumPostReqDto;
import com.example.kindernotification.web.dto.album.AlbumListResDto;
import com.example.kindernotification.web.dto.album.AlbumPatchReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final UserRepository userRepository;

    // 앨범 전체 조회
    public List<AlbumListResDto> getAllAlbum(Long kinderId) {
        List<Album> albumList = albumRepository.findKinderAlbumSelect(kinderId);

        //변환 (Entity -> Dto)
        List<AlbumListResDto> dtos = new ArrayList();
        for (Album album : albumList) {
           dtos.add(AlbumListResDto.builder().album(album).build());
        }

        return dtos;
    }

    // 앨범 상세 조회
    public AlbumDetailResDto getDetailAlbum(Long postId) {
        Album album = albumRepository.findById(postId).orElseThrow(()->new NoSuchElementException("앨범이 없습니다."));

        return AlbumDetailResDto.builder().album(album).build();
    }


    // 앨범 등록
    @Transactional
    public boolean createAlbum(Long kinderId, Long userId, AlbumPostReqDto albumPostReqDto) {
        // 유저 조회
        User user = userRepository.findById(userId).orElseThrow(()->new NoSuchElementException("유저가 없습니다."));

        /** 해당 유저(MANAGER)가 본인 유치원인지만 확인해주면 된다.
         * */
        if (!kinderId.equals(user.getKinder().getId()) || albumPostReqDto.getContents().length() > 2000)
            return false;

        // 앨범 생성
        Album album = Album.builder()
                .user(user)
                .albumPostReqDto(albumPostReqDto)
                .build();

        albumRepository.save(album);

        return true;
    }

    // 앨범 수정
    @Transactional
    public boolean updateAlbum(Long albumId, Long userId, AlbumPatchReqDto albumDto) {
        // 앨범 상세 조회
        Album album = albumRepository.findById(albumId).orElseThrow(() -> new NoSuchElementException("앨범이 없습니다."));

        // 유저 조회
        User user = userRepository.findById(userId).orElseThrow(()-> new NoSuchElementException("유저가 없습니다."));

        /** 이미 앨범 생성할 때 소속 유치원인지 체크했기 때문에 본인이 생성한 앨범인지만 확인하면 된다.
         * */
        if (!album.getUser().equals(user))
            return false;

        // 앨범 수정
        album.update(albumDto);

        albumRepository.save(album);

        return true;
    }

    @Transactional
    public boolean deleteAlbum(Long albumId, Long userId) {
        // 게시글 조회
        Album album = albumRepository.findById(albumId).orElseThrow(()->new NoSuchElementException("앨범이 없습니다."));

        // 유저 조회
        User user = userRepository.findById(userId).orElseThrow(()-> new NoSuchElementException("유저가 없습니다."));

        /** 이미 앨범 생성할 때 소속 유치원인지 체크했기 때문에 본인이 생성한 앨범인지만 확인하면 된다.
         * */
        if(!album.getUser().equals(user))
            return false;

        // 앨범 삭제
        albumRepository.delete(album);

        return true;
    }
}
