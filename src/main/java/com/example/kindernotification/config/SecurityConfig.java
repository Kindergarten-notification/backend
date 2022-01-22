package com.example.kindernotification.config;

import com.example.kindernotification.config.jwt.JwtAccessDeniedHandler;
import com.example.kindernotification.config.jwt.JwtAuthenticationEntryPoint;
import com.example.kindernotification.config.jwt.JwtSecurityConfig;
import com.example.kindernotification.config.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)  // @Secured annotation 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    // 해쉬로 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // Spring Security관련 로직을 수행하지 않도록 담당당
//    @Override
//    public void configure(WebSecurity web) {
//        web
//                .ignoring()
//                .antMatchers(
//                        "/h2-console/**"
//                        , "/favicon.ico"
//                );
//    }

    // spring securiy 설정
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //csrf 토큰 비활성화, 시큐리티에서 js로 요청이오면 csft토큰이 없어서 막아버리는데, disable로 해결
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint) // 401
                .accessDeniedHandler(jwtAccessDeniedHandler) // 403 user -> adminPage access denied

                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                // 세션을 사용하기 않기에 세션 설정을 STATELESS로 지정
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/api/hello").permitAll()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/signup").permitAll()
                .antMatchers("/api/books/{bookId}/detail").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .apply(new JwtSecurityConfig(tokenProvider));
    }

}

