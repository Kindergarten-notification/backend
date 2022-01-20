package com.example.kindernotification.web.service;

import com.example.kindernotification.domain.kinder.Kinder;
import com.example.kindernotification.domain.kinder.KinderRepository;
import com.example.kindernotification.web.dto.MainpageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class MainpageService {
    private final KinderRepository kinderRepository;

    public List<MainpageResponseDto> findAll() {
//        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Kinder> list = kinderRepository.findAll();
        return list.stream().map(MainpageResponseDto::new).collect(Collectors.toList());
    }
}

