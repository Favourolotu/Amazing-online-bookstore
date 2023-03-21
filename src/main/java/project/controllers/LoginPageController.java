package project.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.logic.LoginService;
import project.models.Owner;
import project.models.User;


@Controller
public class LoginPageController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/")
    public String getLoginPage() {
        return "index";
    }

    @PostMapping("/")
    public String handleLoginRequest(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session, Model model) {
        User validatedUser = loginService.validateUserCredentials(username, password);
        Owner validatedOwner = loginService.validateOwnerCredentials(username, password);

        // if a user is found, allow access to user page
        if (validatedUser != null) {
            session.setAttribute("loggedInUser", validatedUser);
            session.removeAttribute("loggedInOwner");
            return "redirect:/user/" + validatedUser.getId();
        }

        // if an owner is found, allow access to owner page
        if (validatedOwner != null) {
            session.setAttribute("loggedInOwner", validatedOwner);
            session.removeAttribute("loggedInUser");
            return "redirect:/owner/" + validatedOwner.getId();
        }

        // If the credentials are invalid, add an error message to the model
        model.addAttribute("errorMessage", "Invalid username or password");
        return "index";
    }
}


