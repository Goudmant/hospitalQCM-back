package com.example.hospitalQCMback.controller;

import com.example.hospitalQCMback.entity.ReponseFinal;
import com.example.hospitalQCMback.service.ReponseFinalJpaController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/reponsefinal")
public class ReponseFinalController {
    private final ReponseFinalJpaController reponseFinalJpaController;

    public ReponseFinalController(ReponseFinalJpaController reponseFinalJpaController) {
        this.reponseFinalJpaController = reponseFinalJpaController;
    }
    @GetMapping
    public List<ReponseFinal> getAllReponseFinal() {
        return reponseFinalJpaController.findReponseFinalEntities();
    }
}
