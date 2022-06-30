package com.example.hospitalQCMback.controller;

import com.example.hospitalQCMback.entity.ReponsesUser;
import com.example.hospitalQCMback.service.ReponsesUserJpaController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/reponsesuser")
public class ReponsesUserController {

    private final ReponsesUserJpaController reponsesUserJpaController;

    public ReponsesUserController(ReponsesUserJpaController reponsesUserJpaController) {
        this.reponsesUserJpaController = reponsesUserJpaController;
    }
    @GetMapping
    public List<ReponsesUser> getAllReponsesUser(){
        return reponsesUserJpaController.findReponsesUserEntities();
    }
}
