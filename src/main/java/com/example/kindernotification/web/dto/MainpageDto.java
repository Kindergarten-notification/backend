package com.example.kindernotification.web.dto;

import com.example.kindernotification.domain.kinder.Kinder;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MainpageDto {

    //ID
    private Long id;
    //유치원코드
    private String kinderCode;
    //유치원명
    private String kinderName;
    //전화번호
    private String telNo;
    //주소
    private String addr;

    @Builder
    public MainpageDto(Kinder entity) {
        this.id = entity.getId();
        this.kinderCode = entity.getKinderCode();
        this.kinderName = entity.getKinderName();
        this.telNo = entity.getTelNo();
        this.addr = entity.getAddr();
    }
}

