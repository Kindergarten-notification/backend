package com.example.kindernotification.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.kindernotification.config.auth.PrincipalDetails;
import com.example.kindernotification.domain.user.User;
import com.example.kindernotification.service.user.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* Security 가 filter 를 갖고 있는데 그 필터 중에 BasicAuthenticationFilter 라는 것이 있음 !
* 여기서 권한, 인증이 필요한 특정 url 을 요청했을 때는 해당 필터를 무조건 탐.
* */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private UserService userService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserService userService) {
        super(authenticationManager);
        this.userService = userService;
    }

    /** 최재은 1/18 (화)
    *  인증이나 권한이 필요한 url 요청이 있을 때 해당 Filter 를 탐.
    * */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//        super.doFilterInternal(request, response, chain);
        System.out.println("JwtAuthorizationFilter: 인증, 권한 필요한 url 요청");
        // header 확인
        String jwtHeader = request.getHeader(JwtProperties.HEADER_STRING);
        if (jwtHeader == null || !jwtHeader.startsWith(JwtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        // JWT token 검증 -> 정상적인 사용자인지 확인
        String jwtToken = request.getHeader(JwtProperties.HEADER_STRING).replace(JwtProperties.TOKEN_PREFIX,"");
        // token 을 통해 사용자 name 추출
        String name = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(jwtToken).getClaim("name").asString();
        // 인증 OK
        if (name != null) {
            System.out.println("user 인증 완료");
            User userEntity = userService.getUserByName(name);
            PrincipalDetails principalDetails = new PrincipalDetails(userEntity);

            // Jwt Token 서명을 통해 서명이 정상이면 Authentication 객체 생성
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    principalDetails,
                    userEntity.getPassword(),
                    principalDetails.getAuthorities());

            // 강제로 security session 에 접근해서 Authentication 객체 생성
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }
}
