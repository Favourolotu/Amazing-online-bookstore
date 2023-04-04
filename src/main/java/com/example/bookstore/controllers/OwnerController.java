package com.example.bookstore.controllers;

import com.example.bookstore.book.Book;
import com.example.bookstore.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/owner")
@PreAuthorize("hasAuthority('OWNER')")
public class OwnerController {

    private final BookService bookService;
    @GetMapping("/add")
    public String addBookPage(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") Book book) {
        bookService.addNewBook(book);
        return "redirect:/home";
    }

    @GetMapping("/edit")
    public String editBookPage(@RequestParam("bookISBN") Integer bookISBN, Model model) {
        model.addAttribute("book", bookService.getBookByISBN(bookISBN));
        return "edit-book";
    }

    @PostMapping("/edit")
    public String editBook(@ModelAttribute Book book, @RequestParam("bookISBN") Integer bookISBN) {
        bookService.editBook(bookISBN, book);
        return "redirect:/home";
    }




}
