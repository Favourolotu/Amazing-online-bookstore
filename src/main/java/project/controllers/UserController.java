package project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

    @GetMapping("/user/{userId}")
    public String getOwnerPage(@PathVariable Long userId, Model model) {
        model.addAttribute("userId", userId);
        return "user";

    }


}
