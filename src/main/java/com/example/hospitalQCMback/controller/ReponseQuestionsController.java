package com.example.hospitalQCMback.controller;

import com.example.hospitalQCMback.entity.ReponseQuestions;
import com.example.hospitalQCMback.service.ReponseQuestionsJpaController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/reponsequestion")
public class ReponseQuestionsController {
    private final ReponseQuestionsJpaController reponseQuestionsJpaController;

    public ReponseQuestionsController(ReponseQuestionsJpaController reponseQuestionsJpaController) {
        this.reponseQuestionsJpaController = reponseQuestionsJpaController;
    }
    @GetMapping
    public List<ReponseQuestions> getAllReponseQuestions(){
        return reponseQuestionsJpaController.findReponseQuestionsEntities();
    }
}
