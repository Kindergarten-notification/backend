package com.example.kindernotification.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;

@Configuration
public class CorsConfig {

    /** 최재은 1/17 (월)
     *  cors 설정 filter
     * */
    @Bean
    public CorsFilter corsFilter2() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowCredentials(true);  // server 가 응답할 때 json 을 자바스크립스에서 처리할 수 있도록 허용함
        configuration.setAllowedOriginPatterns(Collections.singletonList("*"));  // 모든 ip 응답을 허용함
        configuration.setAllowedHeaders(Collections.singletonList("*"));  // 모든 header 응답을 하용함
        configuration.addAllowedMethod("*");  // 모든 method 요청을 허용함
        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }


}
