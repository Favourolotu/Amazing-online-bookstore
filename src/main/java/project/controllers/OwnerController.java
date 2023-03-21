package project.controllers;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.models.Book;
import project.models.Owner;
import project.persistence.BookRepository;
import project.persistence.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class OwnerController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OwnerRepository ownerRepository;


    @GetMapping("/owner")
    public String getOwnerPage(@RequestParam("ownerId") Long ownerId, Model model) {
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(() -> new RuntimeException("Error finding owner with ID: " + ownerId));
        model.addAttribute("owner", owner);
        return "owner";
    }

    @GetMapping("owner/add")
    public String displayAddBookPage(@RequestParam("ownerId") Long ownerId, Model model) {
        model.addAttribute("ownerId", ownerId);
        model.addAttribute("book", new Book());
        return "add-book";
    }

    @PostMapping("owner/add")
    public String addNewBook(@ModelAttribute("book") Book book, @RequestParam("ownerId") Long ownerId, RedirectAttributes redirectAttributes) {
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(() -> new RuntimeException("Error finding owner with ID: " + ownerId));

        book.setOwner(owner);
        bookRepository.save(book);

        redirectAttributes.addAttribute("ownerId", ownerId);
        return "redirect:/owner";
    }

    @GetMapping("owner/edit")
    public String displayEditBookPage(@RequestParam("bookISBN") Long bookISBN, @RequestParam("ownerId") Long ownerId, Model model) {
        Book book = bookRepository.findById(bookISBN).orElseThrow(() -> new RuntimeException("Error finding book with ISBN: " + bookISBN));
        model.addAttribute("book", book);
        model.addAttribute("ownerId", ownerId);
        return "edit-book";
    }

    @PostMapping("owner/edit")
    public String editBook(@RequestParam("bookISBN") Long bookISBN, @ModelAttribute Book editedBook, @RequestParam("ownerId") Long ownerId, RedirectAttributes redirectAttributes) {
        Book book = bookRepository.findById(bookISBN).orElseThrow(() -> new RuntimeException("Error finding book with ISBN: " + bookISBN));
        book.setTitle(editedBook.getTitle());
        book.setAuthor(editedBook.getAuthor());
        book.setDescription(editedBook.getDescription());
        book.setPublisher(editedBook.getPublisher());
        bookRepository.save(book);

        redirectAttributes.addAttribute("ownerId", ownerId);
        return "redirect:/owner";
    }

}
