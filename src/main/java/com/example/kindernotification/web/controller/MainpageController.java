package com.example.kindernotification.web.controller;

import com.example.kindernotification.web.dto.MainpageDto;
import com.example.kindernotification.web.service.MainpageService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class MainpageController {

    private final MainpageService mainpageService;

    @GetMapping("/api/kinders")
    public MainpageDto finalById(@PathVariable Long id) {
        return MainpageService.findById(id);
    }
}

