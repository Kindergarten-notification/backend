package com.example.kindernotification.config.auth;

import com.example.kindernotification.domain.user.User;
import com.example.kindernotification.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) {
        // DB에서 유저정보와 권한정보를 가져오게 됨
        return userRepository.findOneWithAuthoritiesByUsername(username)
                .map(user -> createUser(username, user))
                .orElseThrow(() -> new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    private org.springframework.security.core.userdetails.User createUser(String username, User user) {
//        if (!user.isActivated()) {
//            throw new RuntimeException(username + " -> 활성화되어 있지 않습니다.");
//        }
//
//        //유저가 활성화 상태라면
        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());

        public Collection<? extends GrantedAuthority> user.getAuthorities() {
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add((GrantedAuthority) () -> String.valueOf(user.getRole()));
            return authorities;
        }
        // 유저의 권한, 이름, 패스워드를 갖고 userdetails.User객체를 리턴해준다
        return new org.springframework.security.core.userdetails.User(user.getName(),
                user.getPassword(),
                grantedAuthorities);
    }
}