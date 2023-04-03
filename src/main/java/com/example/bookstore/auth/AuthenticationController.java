package com.example.bookstore.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterRequest request) {
        service.register(request);
        return "redirect:/login";
    }

    @GetMapping("")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "register";
    }

}
