package com.example.kindernotification.web.dto.kinder;

import com.example.kindernotification.domain.kinder.Kinder;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@NoArgsConstructor
public class KinderDetailDto {
    //ID
    private Long id;
    //유치원 코드
    private String kinderCode;
    //유치원 이름
    private String kinderName;
    //시도구
    private String sidoSgg;
    //설립유형
    private String establish;
    //전화번호
    private String telNo;
    //운영시간 data x
    private String operTime;
    //설립자명
    private String ldgrName;
    //주소
    private String addr;
    //홈페이지 대신 미니홈피 링크?
    private String hpAddr;
    //통학차량 운영  다른 db에서 받아와야함
    private String vhclOprnYn;
    // 운행 차량 수
    private String opraVhcnt;

    @Builder
    public KinderDetailDto(Kinder entity) {
        this.id = entity.getId();
        this.kinderCode = entity.getKinderCode();
        this.kinderName = entity.getKinderName();
        this.establish = entity.getEstablish();
        this.telNo = entity.getTelNo();
        this.operTime = entity.getOperTime();
        this.ldgrName = entity.getLdgrName();
        this.sidoSgg = String.valueOf(entity.getSidoSgg());
        this.addr = entity.getAddr();
        this.hpAddr = entity.getHpAddr();
        this.vhclOprnYn = entity.getVhclOprnYn();
        this.opraVhcnt = String.valueOf(entity.getOpraVhcnt());
    }
}
