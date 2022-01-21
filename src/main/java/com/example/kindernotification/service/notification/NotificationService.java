package com.example.kindernotification.service.notification;
import com.example.kindernotification.domain.kinder.Kinder;
import com.example.kindernotification.domain.kinder.KinderRepository;
import com.example.kindernotification.domain.notification.Notification;
import com.example.kindernotification.domain.notification.NotificationRepository;
import com.example.kindernotification.domain.user.User;
import com.example.kindernotification.domain.user.UserRepository;
import com.example.kindernotification.web.dto.NotificationDetailDto;
import com.example.kindernotification.web.dto.NotificationDto;
import com.example.kindernotification.web.dto.NotificationListDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private KinderRepository kinderRepository;

    // 공지사항 조회
    public List<NotificationListDto> selectAllNotification(Long kinderId) {
        //조회 (Entity)
        List<Notification> posts = notificationRepository.findKinderNotificationSelect(kinderId);
        //변환 (Entity -> Dto)
        List<NotificationListDto> dtos = new ArrayList();
        for (int i = 0; i < posts.size(); i++) {
            Notification post = posts.get(i);
            NotificationListDto dto = NotificationListDto.create(post);
            dtos.add(dto);
        }
        return dtos;
    }


    // 공지사항 상세조회
    public NotificationDetailDto selectDetail(Long postId) {
        Notification target = notificationRepository.findById(postId).orElseThrow(()->new IllegalArgumentException("게시판이 없습니다."));


    // 공지사항 등록
    @Transactional
        public NotificationDetailDto createNotification(Long kinderId, Long userId, NotificationDto notificationDto) {
            // 유치원 조회
            Kinder kinder = kinderRepository.findById(kinderId).orElseThrow(()->new IllegalArgumentException("유치원이 없습니다."));

            // 유저 조회
            User user = userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("유저가 없습니다."));

            // 작성자의 유치원 소속 여부 확인
            if (kinder.getId() != user.getKinder().getId())
                throw new IllegalArgumentException("작성자의 소속이 해당유치원이 아닙니다.");

            // 게시글 2000자 글자 등록 취소
            if(notificationDto.getContents().toString().length() > 2000)
                throw new IllegalArgumentException("게시글의 글자가 한도를 초과 하였습니다.");

            // 댓글 엔티티 생성
            Notification notification = Notification.create(kinder, user, notificationDto);

            // 엔티티 DB 저장
            Notification createdPost = notificationRepository.save(notification);

            return NotificationDetailDto.create(notification);
        }

    // 공지사항 수정
    @Transactional
    public NotificationDetailDto updateNotification(Long postId, Long userId, NotificationDto notificationDto) {
        // 게시글 상세 조회
        Notification target = notificationRepository.findById(postId).orElseThrow(()->new IllegalArgumentException("게시판이 없습니다."));

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
        switch (userRole) {
            case "USER":
                // 유저의 권한을 가진자는 자기가 작성한 게시글만 수정가능
                if (target.getUser().getId() != user.getId())
                    throw new IllegalArgumentException("자신이 작성한 게시글만 수정 가능합니다.");
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

                //공지사항 삭제
                @Transactional
                public NotificationDetailDto deletePost(Long postId, Long userId) {
                // 게시글 조회
                Notification target = notificationRepository.findById(postId).orElseThrow(()->new IllegalArgumentException("게시판이 없습니다."));

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
                            throw new IllegalArgumentException("자신이 작성한 게시글만 삭제 가능합니다.");
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
                notificationRepository.delete(target);

                // DTO 변환
                return NotificationDetailDto.create(target);
            }
        }
