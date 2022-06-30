package com.example.hospitalQCMback.controller;

import com.example.hospitalQCMback.entity.ResultatValeurQcm;
import com.example.hospitalQCMback.service.ResultatValeurQcmJpaController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/resultatvaleurqcm")
public class ResultatValeurQcmController {

    private final ResultatValeurQcmJpaController resultatValeurQcmJpaController;

    public ResultatValeurQcmController(ResultatValeurQcmJpaController resultatValeurQcmJpaController) {
        this.resultatValeurQcmJpaController = resultatValeurQcmJpaController;
    }
    @GetMapping
    public List<ResultatValeurQcm> getAllResultatValeurQcm(){
        return resultatValeurQcmJpaController.findResultatValeurQcmEntities();
    }
}
