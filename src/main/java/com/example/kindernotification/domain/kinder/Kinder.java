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

    @Column(nullable = false)
    private String kinderCode;

    @Column(nullable = false)
    private String kinderName;

    @ManyToOne(optional = false)  // 구 하나에 여러 유치원
//    @JoinColumn(name = "SIDOSGG_ID")
    private SidoSgg sidoSgg;
}
