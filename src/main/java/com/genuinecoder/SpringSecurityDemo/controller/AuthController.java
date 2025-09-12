package com.genuinecoder.SpringSecurityDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "custom_login";
    }

    @GetMapping("/admin/panel")
    public String adminPanel() {
        return "admin/panel";
    }

    @GetMapping("/user/profile")
    public String userProfile() {
        return "user/profile"; // Only if you want user pages
    }
}