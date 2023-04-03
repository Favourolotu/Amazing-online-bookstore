package com.example.bookstore.controllers;

import com.example.bookstore.book.Book;
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
    public String addToCart(@RequestParam String username, @RequestParam Integer bookISBN) {
        userService.addToCart(username, bookISBN);
        return "redirect:/user/cart?username=" + username;
    }

    @PostMapping("/checkout")
    @PreAuthorize("#username == authentication.name")
    public String checkout(@RequestParam String username) {
        userService.checkout(username);
        return "redirect:/user/cart?username=" + username;
    }

    //removeFromCart


    //---- recommendation stuff will go here

    //viewRecommendations








}
