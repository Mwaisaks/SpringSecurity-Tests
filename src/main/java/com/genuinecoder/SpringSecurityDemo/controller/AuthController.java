package com.genuinecoder.SpringSecurityDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin/panel";
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