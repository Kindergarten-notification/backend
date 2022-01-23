package com.example.kindernotification.config.jwt;

public interface JwtProperties {
    String SECRET = "sadklfns";
    int EXPIRATION_TIME = 864000000;  // 10 days
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
