package project.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

    @GetMapping("/user/{username}")
    @PreAuthorize("#username == authentication.name")
    public String getOwnerPage(@PathVariable String username, Model model) {
        model.addAttribute("username", username);
        return "user";

    }


}
