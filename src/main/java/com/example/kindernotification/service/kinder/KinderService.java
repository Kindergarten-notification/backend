package com.example.kindernotification.service.kinder;

import com.example.kindernotification.domain.kinder.Kinder;
import com.example.kindernotification.domain.kinder.KinderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KinderService {
    private final KinderRepository kinderRepository;

    public Kinder findById(Long id) {
        return kinderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(id + " 번 유치원 정보가 없습니다."));
    };
}
