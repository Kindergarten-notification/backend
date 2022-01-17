package com.example.kindernotification.domain.sidoSgg;

import com.example.kindernotification.domain.kinder.Kinder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class SidoSgg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)  // 구 (ex. 금천구)
    private int sggCode;

    @Column(unique = true, nullable = false)
    private String sggName;

    @Column(nullable = false)
    private int sidoCode;  // 시 (ex. 서울특별시)

    @Column(nullable = false)
    private String sidoName;

    @OneToMany(mappedBy = "sidoSgg")
    List<Kinder> kinderList = new ArrayList<>();
}