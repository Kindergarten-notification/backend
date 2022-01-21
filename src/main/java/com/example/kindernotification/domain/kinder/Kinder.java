package com.example.kindernotification.domain.kinder;

import com.example.kindernotification.domain.sidoSgg.SidoSgg;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity

public class Kinder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String kinderCode;

    @Column(nullable = false)
    private String kinderName;

    @ManyToOne //(optional = false)  // 구 하나에 여러 유치원
    @JoinColumn(name = "SIDOSGG_ID")
    private SidoSgg sidoSgg;

    // kinder 세부 정보
    private String establish;

    private String eDate;

    private String telNo;

    private String operTime;

    private String ldgrName;  // 원장명

    private String addr;

    private String hpAddr;

    private String vhclOprnYn;  // 차량 운영 여부

    private int opraVhcnt;  // 운행 차량 수
}
