package com.example.hospitalQCMback.controller;

import com.example.hospitalQCMback.entity.Theme;
import com.example.hospitalQCMback.service.ThemeJpaController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("theme")
public class ThemeController {

    public final ThemeJpaController themeJpaController;

    public ThemeController(ThemeJpaController themeJpaController) {
        this.themeJpaController = themeJpaController;
    }
    @GetMapping
    public List<Theme> getAllTheme() {
        return themeJpaController.findThemeEntities();
    }
}
