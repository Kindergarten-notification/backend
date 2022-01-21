package com.example.kindernotification.web.dto;

import com.example.kindernotification.domain.kinder.Kinder;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter
@NoArgsConstructor
public class MainpageResponseDto {

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

   public MainpageResponseDto(Kinder entity) {
       this.id = entity.getId();
       this.kinderCode = entity.getKinderCode();
       this.kinderName = entity.getKinderName();
       this.telNo = entity.getTelNo();
       this.addr = entity.getAddr();
   }
}

