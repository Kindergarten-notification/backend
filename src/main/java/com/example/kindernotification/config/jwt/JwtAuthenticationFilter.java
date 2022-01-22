package com.example.kindernotification.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.kindernotification.config.auth.PrincipalDetails;
import com.example.kindernotification.web.dto.user.LoginReqDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


/**
 * Spring Security 에 UsernamePasswordAuthenticationFilter 있음.
 * "/login" 요청해서 username, password 전송 시 (POST), 해당 필더 동작 !
 * formLogin().disable() 때문에 동작하지 않음
 * */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    /** 최재은 1/18 (화)
     * "/login" 요청 시, 로그인 시도를 위해 실행되는 method
     * 1. username, password 받아서
     * 2. 정상인지 로그인 시도
     *     2-1. authenticationManager 로 로그인 시도하면, PrincipalDetailsService 호출
     *     2-2 loadUserByUsername() method 실행
     * 3. PrincipalDetails 를 session 에 담고  (session 에 담아야 권한 관리 가능)
     * 4. JWT token 을 생성해 응답
     * */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            // 1
            ObjectMapper objectMapper = new ObjectMapper();
            LoginReqDto loginReqDto = objectMapper.readValue(request.getInputStream(), LoginReqDto.class);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginReqDto.getName(), loginReqDto.getPassword());

            // 2
            // loadUserByUsername() 함수가 실행된 후 정상이면 authentication 리턴
            // -> DB 에 있는 username 과 password 일치
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            // 로그인이 정상적으로 됐는지 확인
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            System.out.println(principalDetails.getUser().getName());

            // 3
            return authentication;  // session 에 저장 (권한처리)
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /** 최재은 1/18 (화)
     * attemptAuthentication 실행 후, 인증이 정상적으로 되었으면 successfulAuthentication method 실행
     * header [ "Authorization" : {JWT token} ] 추가해서 return 해줌
     * */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("successfulAuthentication: 인증 완료");
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        // Hash 암호 방식
        String jwtToken = JWT.create()
                .withSubject(principalDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))  // 만료 시간 10m
                .withClaim("id", principalDetails.getUser().getId())  // 비공개 claim
                .withClaim("name", principalDetails.getUser().getName())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));  // server 가 아는 고유한 값 (secret key)

        System.out.println("token: " + jwtToken);

        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken);

        System.out.println("response getHeader : " +response.getHeaderNames());
        System.out.println("Authorization:  " +response.getHeader("Authorization"));
    }
}
