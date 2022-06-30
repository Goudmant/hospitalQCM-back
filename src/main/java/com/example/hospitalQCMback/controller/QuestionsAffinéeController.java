package com.example.hospitalQCMback.controller;

import com.example.hospitalQCMback.entity.QuestionsAffinée;
import com.example.hospitalQCMback.service.QuestionsAffinéeJpaController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/questionsaffinee")
public class QuestionsAffinéeController {

    private final QuestionsAffinéeJpaController questionsAffinéeJpaController;

    public QuestionsAffinéeController(QuestionsAffinéeJpaController questionsAffinéeJpaController) {
        this.questionsAffinéeJpaController = questionsAffinéeJpaController;
    }
    @GetMapping
    public List<QuestionsAffinée> getAllQuestionAffinée() {
        return questionsAffinéeJpaController.findQuestionsAffinéeEntities();
    }
}
