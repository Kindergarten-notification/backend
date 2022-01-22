package com.example.kindernotification.service;

import com.example.kindernotification.domain.kinder.Kinder;
import com.example.kindernotification.domain.kinder.KinderRepository;
import com.example.kindernotification.web.dto.kinder.KinderListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class MainService {
    private final KinderRepository kinderRepository;

    public List<KinderListDto> findAll(int pageNum) {
//        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Page<Kinder> list = kinderRepository.findAllByOrderByKinderName(PageRequest.of(pageNum-1, 10));
        return list.stream().map(KinderListDto::new).collect(Collectors.toList());
    }
}

