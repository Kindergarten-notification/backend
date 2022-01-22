package com.example.kindernotification.web.controller;

import com.example.kindernotification.service.user.UserService;
import com.example.kindernotification.web.dto.user.JoinReqDto;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class RestApiController {

    private final UserService userService;
//    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/join")
    public String join(@RequestBody JoinReqDto joinReqDto) {
//        joinReqDto.setPassword(passwordEncoder.encode(joinReqDto.getPassword()));
        userService.join(joinReqDto);
        return "success join";
    }
}
