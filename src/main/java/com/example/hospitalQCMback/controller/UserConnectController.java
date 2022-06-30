package com.example.hospitalQCMback.controller;

import com.example.hospitalQCMback.entity.UserConnect;
import com.example.hospitalQCMback.service.UserConnectJpaController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/userconnect")
public class UserConnectController {

    public final UserConnectJpaController userConnectJpaController;

    public UserConnectController(UserConnectJpaController userConnectJpaController) {
        this.userConnectJpaController = userConnectJpaController;
    }
    @GetMapping
    public List<UserConnect> GetAllUserConnect(){
        return userConnectJpaController.findUserConnectEntities();
    }
}
