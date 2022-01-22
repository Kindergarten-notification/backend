package com.example.kindernotification.domain.user;

import com.example.kindernotification.domain.BaseTimeEntity;
import com.example.kindernotification.domain.kinder.Kinder;

import com.example.kindernotification.web.dto.user.JoinReqDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity

public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, unique = true, nullable = false)
    private String email;

    @Column(length = 45, nullable = false)
    private String nickname;

    @Column(length = 45, nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "KINDER_ID")
    @JsonIgnore
    private Kinder kinder;

    @Builder
    public User(JoinReqDto joinReqDto, Kinder kinder) {
        this.email = joinReqDto.getEmail();
        this.nickname = joinReqDto.getNickname();
        this.name = joinReqDto.getName();
        this.password = joinReqDto.getPassword();
        this.role = joinReqDto.getRole();
        this.kinder = kinder;
    }
}
