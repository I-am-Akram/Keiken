package com.keiken.chatgptapi.app.user.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginPage() {
        return "app/login/login";
    }

    @GetMapping("/signin")
    public String getSigninPage() {
        return "app/login/signin";
    }

    @GetMapping("/signup")
    public String getSignupPage() {
        return "app/login/signup";
    }

}
