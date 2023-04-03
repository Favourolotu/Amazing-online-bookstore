package com.example.bookstore.controllers;

import com.example.bookstore.book.Book;
import com.example.bookstore.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {

    private final BookService bookService;

    @GetMapping("")
    public String home(Model model) {
        Iterable<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "home";
    }
    @GetMapping("/search")
    public String search(@RequestParam String query, Model model) {
        model.addAttribute("books", bookService.getBookByQuery(query));
        return "search";
    }



}
