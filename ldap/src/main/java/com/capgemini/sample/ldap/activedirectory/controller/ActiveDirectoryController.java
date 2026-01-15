package com.capgemini.sample.ldap.activedirectory.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ad")
public class ActiveDirectoryController {

    @GetMapping("/hello")
    public String hello(Authentication authentication) {
        return "Hola AD user: " + authentication.getName();
    }
}
