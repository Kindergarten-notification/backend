package com.example.kindernotification.web.service;

import com.example.kindernotification.domain.kinder.Kinder;
import com.example.kindernotification.domain.kinder.KinderRepository;
import com.example.kindernotification.web.dto.MainpageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class MainpageService {
    private static KinderRepository kinderRepository;

    public static MainpageDto findById(Long id){
        Kinder entity = kinderRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("조회불가능"));
   return new MainpageDto(entity);
    }

//    @Transactional(readOnly = true)
//    public List<MainpageDto> findAll() {
//        return kinderRepository.findAll().stream()
//                .map(kinder->new MainpageDto((Kinder)kinder))
//                .collect(Collectors.toList());
    }

