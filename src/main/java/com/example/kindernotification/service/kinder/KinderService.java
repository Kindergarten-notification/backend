package com.example.kindernotification.service.kinder;

import com.example.kindernotification.domain.kinder.Kinder;
import com.example.kindernotification.domain.kinder.KinderRepository;
import com.example.kindernotification.web.dto.KinderDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class KinderService {
    private final KinderRepository kinderRepository;

    public KinderDetailDto findById(Long id) {
        Kinder entity = kinderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("없는 유치원입니다. id=" + id));

        return new KinderDetailDto(entity);
    }

    //desc kinder repository 에 추가
    @Transactional(readOnly = true)
    public List<KinderDetailDto> findAllDesc() {
        return kinderRepository.findAllOrderByIdDesc().stream()
                .map(KinderDetailDto::new)
                .collect(Collectors.toList());
    }
}


