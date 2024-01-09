package com.medico.app.controller;


import com.medico.app.service.KeycloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final KeycloakService keycloakService;

    @Autowired
    public UserController(KeycloakService keycloakService) {
        this.keycloakService = keycloakService;
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestParam("username") String username,
                                 @RequestParam("newPassword") String newPassword) {
        keycloakService.changeUserPassword(username, newPassword);
        return "Password changed successfully!";
    }
}