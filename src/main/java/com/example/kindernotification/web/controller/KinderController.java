package com.example.kindernotification.web.controller;

import com.example.kindernotification.service.kinder.KinderDetailService;
import com.example.kindernotification.web.dto.KinderDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class KinderController {

    private final KinderDetailService kinderDetailService;

    @GetMapping("/kinder/{kinder_id}")
    public KinderDetailDto kinderDetail(@PathVariable(name = "kinder_id") Long id) {
        return kinderDetailService.findById(id);
    }

}



