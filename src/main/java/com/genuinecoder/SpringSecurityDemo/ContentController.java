package com.genuinecoder.SpringSecurityDemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {

    @GetMapping(path = "/home")
    public String handleWelcome(){
        return "home";
    }

    @GetMapping(path = "/admin/homer")
    public String handleAdminsHome(){
        return "home_admin";
    }

    @GetMapping(path = "/user/home")
    public String handleUserHome(){
        return "home_user";
    }
}
