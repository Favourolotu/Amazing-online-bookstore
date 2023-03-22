package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import project.models.Book;
import project.persistence.BookRepository;

@Controller
public class UserController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/user/{username}")
    @PreAuthorize("#username == authentication.name")
    public String getOwnerPage(@PathVariable String username, Model model) {
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("username", username);
        model.addAttribute("books", books);
        return "user";
    }






}
