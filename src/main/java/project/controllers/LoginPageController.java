package project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginPageController {


    /**
     * The initial entry point of the entire application
     *
     * @param model
     * @return the initial login view
     */
    @GetMapping("/startPage")
    public String logoutUser(Model model) {
        return "login-page";
    }
}
