package com.example.hospitalQCMback.controller;


import com.example.hospitalQCMback.entity.Contact;
import com.example.hospitalQCMback.service.ContactJpaController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RequestMapping("/contact")
@RestController
public class ContactController {


    private final ContactJpaController contactJpaController;

    public ContactController(ContactJpaController contactJpaController) {
        this.contactJpaController = contactJpaController;
    }

    @GetMapping
    public List<Contact> getAllContact() {
        return contactJpaController.findContactEntities();
    }

}
