package com.example.kindernotification.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class Encoder {
    /** 최재은 1/17 (월)
     * password encode 해주는 method
     * 해당 method 의 return object 를 IoC 로 등록
     **/
    @Bean
    public BCryptPasswordEncoder encoderPassword() {
        return new BCryptPasswordEncoder();
    }
}
