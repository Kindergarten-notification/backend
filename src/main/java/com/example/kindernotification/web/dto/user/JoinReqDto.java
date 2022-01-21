package com.example.kindernotification.web.dto.user;

import com.example.kindernotification.domain.user.Role;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JoinReqDto {
    private String email;
    private String nickname;
    private String name;
    private String password;
    private Role role;
    private Long kinderId;
}
