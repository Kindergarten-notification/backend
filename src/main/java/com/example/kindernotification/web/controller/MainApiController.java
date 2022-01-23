package com.example.kindernotification.web.controller;

import com.example.kindernotification.web.dto.kinder.KinderListDto;
import com.example.kindernotification.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class MainApiController {

    private final MainService mainService;

    @GetMapping("/api/kinders")
    public List<KinderListDto> findAll(@RequestParam(name = "page") int pageNum){
        return mainService.findAll(pageNum);
    }
}

