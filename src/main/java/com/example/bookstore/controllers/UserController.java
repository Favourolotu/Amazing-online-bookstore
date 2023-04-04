package com.example.bookstore.controllers;

import com.example.bookstore.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/cart")
    @PreAuthorize("#username == authentication.name")
    public String viewShoppingCart(Model model, @RequestParam String username) {
        model.addAttribute("cart", userService.getShoppingCart(username));
        return "cart";
    }

    @PostMapping("/cart")
    @PreAuthorize("#username == authentication.name")
    public String addToCart(@RequestParam String username, @RequestParam Integer bookISBN, @RequestParam Integer quantity) {
        userService.addToCart(username, bookISBN, quantity);
        return "redirect:/user/cart?username=" + username;
    }

    @PostMapping("/remove")
    @PreAuthorize("#username == authentication.name")
    public String removeFromCart(@RequestParam String username, @RequestParam Integer bookISBN) {
        userService.removeFromCart(username, bookISBN);
        return "redirect:/user/cart?username=" + username;
    }


    @PostMapping("/checkout")
    @PreAuthorize("#username == authentication.name")
    public String checkout(@RequestParam String username) {
        userService.checkout(username);
        return "redirect:/user/cart?username=" + username;
    }


    @GetMapping("/recommendations")
    @PreAuthorize("#username == authentication.name")
    public String viewRecommendations(@RequestParam String username, Model model) {
        model.addAttribute("books", userService.getRecommendations(username));
        return "home";
    }

}
