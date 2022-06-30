package com.example.hospitalQCMback.controller;

import com.example.hospitalQCMback.entity.Medecin;
import com.example.hospitalQCMback.service.MedecinJpaController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/medecin")
public class MedecinController {

    private final MedecinJpaController medecinJpaController;

    public MedecinController(MedecinJpaController medecinJpaController) {
        this.medecinJpaController = medecinJpaController;
    }
    @GetMapping
    public List<Medecin> getAllMedecin() {
        return medecinJpaController.findMedecinEntities();
    }
}
