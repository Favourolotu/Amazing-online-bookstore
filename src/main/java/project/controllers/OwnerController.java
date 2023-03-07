package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.models.Book;
import project.persistence.BookRepository;

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

    @GetMapping("owner/addBook")
    public String addBook() {
        return "add-book";
    }

    @PostMapping("owner/addBook")
    public String addBook(@RequestParam("title") String title, @RequestParam("author") String author) {
        Book book = new Book(title, author);
        bookRepository.save(book);
        return "redirect:/owner";
    }

    @GetMapping("owner/editBook")
    public String editBook(@RequestParam("bookID") Long bookID, Model model) {
        Book book = bookRepository.findById(bookID).orElseThrow(() -> new RuntimeException("Error finding book with ID: " + bookID));
        model.addAttribute("book", book);
        return "edit-book";
    }

    @PostMapping("owner/editBook")
    public String editBook(@RequestParam("bookID") Long bookID, @RequestParam("title") String title, @RequestParam("author") String author) {
        Book book = bookRepository.findById(bookID).orElseThrow(() -> new RuntimeException("Error finding book with ID: " + bookID));
        book.setTitle(title);
        book.setAuthor(author);
        bookRepository.save(book);
        return "redirect:/owner";
    }





}
