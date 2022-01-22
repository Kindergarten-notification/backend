package com.example.kindernotification.web.controller;

import com.example.kindernotification.config.jwt.JwtFilter;
import com.example.kindernotification.config.jwt.TokenProvider;
import com.example.kindernotification.domain.user.UserRepository;
import com.example.kindernotification.web.dto.TokenDto;
import com.example.kindernotification.web.dto.user.LoginReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class AuthApiController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserRepository userRepository;

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<TokenDto> authorize(@RequestBody LoginReqDto loginDto) {

        // username, password를 통해 AuthenticationToken을 만듬
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getName(), loginDto.getPassword());

        log.info("authentication " + authenticationToken);

        // 인증된Token으로 인증을 만들때(authenticate 메소드가 실행이 될때, CustomUserDetailsService.loadUserByUsername 메소드가 실행된다)
        // loadUserByUsername메서드에서 리턴받은 user객체로 Authentication객체 생성
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // 생성된 객체를 SecurityContext에 저장
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 생성된 객체로 TokenProvider.createToken 메서드를 통해 jwt토큰을 생성
        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        // Header에 추가
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        /** userID를 찾아와야됨 */
        Long findUserId = userRepository.findByName(loginDto.getName()).getId();

        // jwt토큰을 body에도 추가         body            header          status
        return new ResponseEntity<>(new TokenDto(jwt, findUserId), httpHeaders, HttpStatus.OK);
    }
}