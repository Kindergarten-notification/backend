package com.example.kindernotification.config;

import com.example.kindernotification.config.jwt.CustomAuthenticationEntryPoint;
import com.example.kindernotification.config.jwt.JwtAuthenticationFilter;
import com.example.kindernotification.config.jwt.JwtAuthorizationFilter;
import com.example.kindernotification.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.filter.CorsFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)  // @Secured annotation 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    private final CorsFilter corsFilter;
    private final UserService userService;

    /** 최재은 1/18 (화)
     * Security 설정 method
     * JwtAuthenticationFilter, JwtAuthorizationFilter:
     *   - AuthenticationManager 를 parameter 로 전달 (WebSecurityConfigurerAdapter 가 들고 있음)
     *
     * */
    /*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // SecurityContextPersistenceFilter: Security chain 에서 가장 먼저 실행되는 Filter
         http.addFilterBefore(new JwtAuthenticationFilter(authenticationManager()), SecurityContextPersistenceFilter.class);
        http.addFilterBefore(new JwtAuthorizationFilter(authenticationManager(), userService), SecurityContextPersistenceFilter.class);
        http.csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // session 사용 안함
            .and()
//            .addFilter(corsFilter)
            .formLogin().disable()
            .httpBasic().disable()
//            .addFilter(new JwtAuthenticationFilter(authenticationManager()))
//                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userService))
            .authorizeRequests()
                    .antMatchers("/api/user/**")
                        .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                    .antMatchers("/api/manager/**")
                        .access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                    .antMatchers("/api/admin/**")
                        .access("hasRole('ROLE_ADMIN')")
                                .anyRequest().permitAll()
            .and().exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint());
    }
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //csrf 토큰 비활성화, 시큐리티에서 js로 요청이오면 csft토큰이 없어서 막아버리는데, disable로 해결
                .csrf().disable()
                .exceptionHandling()
//                .authenticationEntryPoint(new CustomAuthenticationEntryPoint()) // 401
//                .accessDeniedHandler(jwtAuthenticationEntryPoint) // 403 user -> adminPage access denied

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
                .loginPage("/login");
    }
}
