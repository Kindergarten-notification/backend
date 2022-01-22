package com.example.kindernotification.web.controller;

import com.example.kindernotification.web.dto.MainpageResponseDto;
import com.example.kindernotification.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class MainApiController {

    private final MainService mainService;

    @GetMapping("/api/kinders")
    public List<MainpageResponseDto> findAll(@RequestParam(name = "page") int pageNum){
        return mainService.findAll(pageNum);
    }
}

