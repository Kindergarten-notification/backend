package com.example.kindernotification.service.album;

import com.example.kindernotification.domain.album.Album;
import com.example.kindernotification.domain.album.AlbumRepository;
import com.example.kindernotification.domain.kinder.Kinder;
import com.example.kindernotification.domain.kinder.KinderRepository;
import com.example.kindernotification.domain.user.User;
import com.example.kindernotification.domain.user.UserRepository;
import com.example.kindernotification.web.dto.AlbumDetailDto;
import com.example.kindernotification.web.dto.AlbumDto;
import com.example.kindernotification.web.dto.AlbumListDto;
import com.example.kindernotification.web.dto.AlbumUpdateReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final UserRepository userRepository;
    private final KinderRepository kinderRepository;

    // 앨범 전체 조회
    public List<AlbumListDto> getAllAlbum(Long kinderId) {
        //조회 (Entity)
        List<Album> albumList = albumRepository.findKinderAlbumSelect(kinderId);

        //변환 (Entity -> Dto)
        List<AlbumListDto> dtos = new ArrayList();

        for (Album album : albumList) {
           dtos.add(AlbumListDto.builder().album(album).build());
        }

        return dtos;
    }

    // 앨범 상세 조회
    public AlbumDetailDto getDetailAlbum(Long postId) {
        Album album = albumRepository.findById(postId).orElseThrow(()->new IllegalArgumentException("앨범이 없습니다."));

        return AlbumDetailDto.create(album);
    }

    // 앨범 등록
    @Transactional
    public AlbumDetailDto createAlbum(Long kinderId, Long userId, AlbumDto albumDto) {
        // 유치원 조회
        Kinder kinder = kinderRepository.findById(kinderId).orElseThrow(()->new IllegalArgumentException("유치원이 없습니다."));

        // 유저 조회
        User user = userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("유저가 없습니다."));

        // 작성자의 유치원 소속 여부 확인
        if (kinder.getId() != user.getKinder().getId())
            throw new IllegalArgumentException("작성자의 소속이 해당유치원이 아닙니다.");

        // 게시글 2000자 글자 등록 취소
        if(albumDto.getContents().length() > 2000)
            throw new IllegalArgumentException("게시글의 글자가 한도를 초과 하였습니다.");

        // 댓글 엔티티 생성
        Album album = Album.create(kinder, user, albumDto);

        // 엔티티 DB 저장
        albumRepository.save(album);

        return AlbumDetailDto.create(album);
    }

    // 앨범 수정
    @Transactional
    public AlbumDetailDto updateAlbum(Long postId, Long userId, AlbumUpdateReqDto albumDto) {
        // 앨범 상세 조회
        Album target = albumRepository.findById(postId).orElseThrow(()->new IllegalArgumentException("앨범이 없습니다."));

        // 유저 조회
        User user = userRepository.findById(userId).orElseThrow(()-> new IllegalArgumentException("유저가 없습니다."));

        // 게시글의 유치원과 작성자의 유치원 소속 여부 확인
        if (target.getKinder().getId() != user.getKinder().getId())
            throw new IllegalArgumentException("게시글의 유치원 소속이 작성자의 소속 유치원이 아닙니다.");

        // USER, MANAGER, ADMIN,
        // 조회한 유저의 권한
        String userRole = user.getRole().toString();

        // 권한 여부
        // 0 - 권한을 부여하지 않음
        // 1 - 권한을 부여함
        int sucess = 0;

        // 권한 설정
        switch (userRole){
            case "ROLE_USER":
                // 유저의 권한을 가진자는 자기가 작성한 게시글만 수정가능
                if(target.getUser().getId() != user.getId())
                    throw new IllegalArgumentException("자신이 작성한 게시글만 수정 가능합니다.");
                sucess = 1;
                break;
            case "ROLE_MANAGER":
                sucess = 1;
                break;
            case "ROLE_ADMIN":
                sucess = 1;
                break;
            default:
                sucess = 0;
        }

        // 권한을 부여받지 못하면
        if(sucess != 1)
            throw new IllegalArgumentException("수정권한이 없습니다.");

        // 게시글 수정
        target.update(albumDto);

        // 엔티티 DB 저장
        Album updatedAlbum = albumRepository.save(target);

        return AlbumDetailDto.create(updatedAlbum);
    }

    @Transactional
    public AlbumDetailDto deleteAlbum(Long postId, Long userId) {
        // 게시글 조회
        Album target = albumRepository.findById(postId).orElseThrow(()->new IllegalArgumentException("앨범이 없습니다."));

        // 유저 조회
        User user = userRepository.findById(userId).orElseThrow(()-> new IllegalArgumentException("유저가 없습니다."));

        // 게시글의 유치원과 작성자의 유치원 소속 여부 확인
        if (target.getKinder().getId() != user.getKinder().getId())
            throw new IllegalArgumentException("게시글의 유치원 소속이 작성자의 소속 유치원이 아닙니다.");

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
                // 유저의 권한을 가진자는 자기가 작성한 게시글만 수정가능
                if(target.getUser().getId() != user.getId())
                    throw new IllegalArgumentException("자신이 작성한 앨범만 삭제 가능합니다.");
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
            throw new IllegalArgumentException("삭제 권한이 없습니다.");

        // 조회된 게시글 DB에서 삭제
        albumRepository.delete(target);

        // DTO 변환
        return AlbumDetailDto.create(target);
    }
}
