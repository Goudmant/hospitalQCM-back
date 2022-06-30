package com.example.hospitalQCMback.controller;

import com.example.hospitalQCMback.entity.Questions;
import com.example.hospitalQCMback.service.QuestionsJpaController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/questions")
public class QuestionsController {

    private final QuestionsJpaController questionsJpaController;

    public QuestionsController(QuestionsJpaController questionsJpaController) {
        this.questionsJpaController = questionsJpaController;
    }
    @GetMapping
    public List<Questions> getAllQuestions() {
        return questionsJpaController.findQuestionsEntities();
    }
}
