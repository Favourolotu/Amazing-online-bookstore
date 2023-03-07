package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import project.models.Book;
import project.persistence.BookRepository;

import java.util.Optional;

@Controller
public class OwnerController {
    @Autowired
    private BookRepository bookRepository;

    //Default page for an owner
    @GetMapping("/owner")
    public String displayOwnerPage(Model model) {
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "owner";
    }

    //TODO redirects to a page for inputting all the sections of a book
    @PostMapping("owner/addBook")
    public String addBook(Model model) {
        Book book = new Book("new_book_title", "new_book_author");
        bookRepository.save(book);
        return "redirect:/owner";
    }

    //TODO redirects to a page for editing all the sections of the selected book
    @PostMapping("owner/editBook")
    public String editBook(@RequestParam("bookID") Long bookID, Model model) {
        Book book = bookRepository.findById(bookID).orElseThrow(() -> new RuntimeException("Error finding book with ID: " + bookID));
        model.addAttribute("book", book);
        return "edit-book";
    }





}
