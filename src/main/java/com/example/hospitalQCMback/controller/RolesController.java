package com.example.hospitalQCMback.controller;

import com.example.hospitalQCMback.entity.Roles;
import com.example.hospitalQCMback.service.RolesJpaController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/roles")
public class RolesController {

    public final RolesJpaController rolesJpaController;

    public RolesController(RolesJpaController rolesJpaController) {
        this.rolesJpaController = rolesJpaController;
    }
    @GetMapping
    public List<Roles> getAllRoles(){
        return rolesJpaController.findRolesEntities();
    }
}
