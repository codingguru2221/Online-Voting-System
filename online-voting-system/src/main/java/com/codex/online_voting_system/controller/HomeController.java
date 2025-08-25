package com.codex.online_voting_system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Welcome to the Online Voting System!";
    }

    @GetMapping("/test")
    public String test() {
        return "Test API is working!";
    }
}
