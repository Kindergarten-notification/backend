package com.example.kindernotification.web.controller;

import com.example.kindernotification.service.album.AlbumService;
import com.example.kindernotification.web.dto.album.AlbumDetailResDto;
import com.example.kindernotification.web.dto.album.AlbumPostReqDto;
import com.example.kindernotification.web.dto.album.AlbumListResDto;
import com.example.kindernotification.web.dto.album.AlbumPatchReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AlbumApiController {
    private final AlbumService albumService;

    /** 특정 유치원의 album 전체 목록 가져오기
     *
     * return
     * - List<AlbumListResDto>
     * */
    @GetMapping("/albums")
    public ResponseEntity<List<AlbumListResDto>> getAllAlbum(@RequestParam("kinderId") Long kinderId){
        List<AlbumListResDto> dtos = albumService.getAllAlbum(kinderId);

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    /** id 로 특정 album 가져오기
     *
     * return
     *  - AlbumDetailResDto
     * */
    @GetMapping("/album/{id}")
    public ResponseEntity<AlbumDetailResDto> getDetailAlbum(@PathVariable("id") Long albumId){
        AlbumDetailResDto dto = albumService.getDetailAlbum(albumId);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    /** album 생성
     * - 유저(ROLE_MANAGER)가 앨범을 생성하려는 유치원 소속인지 체크
     *
     * return
     * - 등록 완료: status 201
     * - 등록 실패: status 400
     * */
    @Secured({"ROLE_MANAGER", "ROLE_ADMIN"})
    @PostMapping("/album")
    public ResponseEntity createAlbum (@RequestParam("kinder_id") Long kinderId,
                                                          @RequestParam("user_id") Long userId,
                                                          @RequestBody AlbumPostReqDto albumPostReqDto){
        System.out.println("앨범 생성");
        if (albumService.createAlbum(kinderId, userId, albumPostReqDto)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Completed to create an album.\n");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create an album.\n");
    }

    /** album 수정
     * - 해당 앨범을 생성한 유저인지만 체크
     *    - 앨범 생성 시, 이미 소속 유치원인지 확인했기 때문에 체크할 필요 없음
     *
     * return
     * - 수정 완료: status 200
     * - 수정 실패: status 400
     * */
    @Secured({"ROLE_MANAGER", "ROLE_ADMIN"})
    @PatchMapping("/album/{id}")
    public ResponseEntity updateAlbum (@PathVariable("id") Long albumId,
                                                          @RequestParam("user_id") Long userId,
                                                          @RequestBody AlbumPatchReqDto albumDto){
        if (albumService.updateAlbum(albumId, userId, albumDto)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Completed to update an album.\n");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update an album.\n");
    }

    /** album 삭제
     * - 해당 앨범을 생성한 유저인지만 체크
     *    - 앨범 생성 시, 이미 소속 유치원인지 확인했기 때문에 체크할 필요 없음
     *
     * return
     * - 수정 완료: status 200
     * - 수정 실패: status 400
     * */
    @Secured({"ROLE_MANAGER", "ROLE_ADMIN"})
    @DeleteMapping("/album/{id}")
    public ResponseEntity deleteAlbum (@PathVariable("id") Long albumId,
                                                          @RequestParam("user_id") Long userId){

        if (albumService.deleteAlbum(albumId, userId)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Completed to delete an album.\n");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to delete an album.\n");
    }

}
