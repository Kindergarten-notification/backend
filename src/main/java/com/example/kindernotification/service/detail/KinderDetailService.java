package com.example.kindernotification.service.detail;

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
public class KinderDetailService {
    public static KinderRepository kinderRepository;

    public static KinderDetailDto findById(Long id) {
        Kinder entity = kinderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("없는 유치원입니다. id=" + id));

        return new KinderDetailDto(entity);
    }

    //desc kinder repository에 추가
    @Transactional(readOnly = true)
    public List<KinderDetailDto> findAllDesc() {
        return kinderRepository.findAllDesc().stream()
                .map(KinderDetailDto::new)
                .collect(Collectors.toList());
    }
}


