package com.example.kindernotification.web.controller;

import com.example.kindernotification.web.dto.MainpageResponseDto;
import com.example.kindernotification.web.service.MainpageService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class MainpageController {

    private final MainpageService mainpageService;

    @GetMapping("/api/kinders")
    public List<MainpageResponseDto> findAll(@RequestParam(name = "page") int pageNum){
        return mainpageService.findAll(pageNum);
    }
}

