package com.example.kindernotification.web.controller;

import com.example.kindernotification.service.detail.KinderDetailService;
import com.example.kindernotification.web.dto.KinderDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class KinderDetailController {

    private final KinderDetailService kinderDetailService;

    @GetMapping("/")
    public KinderDetailDto findById(@PathVariable Long id) {
        return KinderDetailService.findById(id);
    }

}



