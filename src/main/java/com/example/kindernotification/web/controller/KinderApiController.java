package com.example.kindernotification.web.controller;

import com.example.kindernotification.service.kinder.KinderService;
import com.example.kindernotification.web.dto.KinderDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class KinderApiController {

    private final KinderService kinderService;

    @GetMapping("/kinder/{kinder_id}")
    public KinderDetailDto kinderDetail(@PathVariable(name = "kinder_id") Long id) {
        return kinderService.findById(id);
    }

}



