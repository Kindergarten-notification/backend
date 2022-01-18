package com.example.kindernotification.service.kinder;

import com.example.kindernotification.domain.kinder.Kinder;
import com.example.kindernotification.domain.kinder.KinderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class KinderService {
    private final KinderRepository kinderRepository;

    public Kinder findById(Long id) {
        Optional<Kinder> kinderEntity = kinderRepository.findById(id);

        return kinderEntity.get();
    };
}
