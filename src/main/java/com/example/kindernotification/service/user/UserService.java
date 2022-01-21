package com.example.kindernotification.service.user;

import com.example.kindernotification.domain.user.User;
import com.example.kindernotification.domain.user.UserRepository;
import com.example.kindernotification.service.kinder.KinderService;
import com.example.kindernotification.web.dto.user.JoinReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final KinderService kinderService;
    private final UserRepository userRepository;

    @Transactional
    public void join(JoinReqDto joinReqDto) {
        User user = User.builder()
                .joinReqDto(joinReqDto)
                .kinder(kinderService.getKinder(joinReqDto.getKinderId()))
                .build();
        userRepository.save(user);
    }

    public User findByName(String name) {
        return userRepository.findByName(name);
    }
}
