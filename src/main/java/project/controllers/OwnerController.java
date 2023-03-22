package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.logic.OwnerService;
import project.models.Book;

@Controller
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping("/owner/{username}")
    @PreAuthorize("#username == authentication.name")
    public String getOwnerPage(@PathVariable String username, Model model) {

        model.addAttribute("owner", ownerService.getOwnerByUsername(username));
        return "owner";

    }

    @GetMapping("/owner/{username}/add")
    @PreAuthorize("#username == authentication.name")
    public String getAddBookPage(@PathVariable String username, Model model) {

        model.addAttribute("owner", ownerService.getOwnerByUsername(username));
        model.addAttribute("book", new Book());
        return "add-book";

    }

    @GetMapping("/owner/{username}/edit")
    @PreAuthorize("#username == authentication.name")
    public String getEditBookPage(@PathVariable("username") String username, @RequestParam("bookISBN") Long bookISBN, Model model) {

        model.addAttribute("owner", ownerService.getOwnerByUsername(username));
        model.addAttribute("book", ownerService.getBookById(bookISBN));
        return "edit-book";

    }

    @PostMapping("owner/{username}/add")
    @PreAuthorize("#username == authentication.name")
    public String addNewBook(@ModelAttribute("book") Book book, @PathVariable("username") String username) {

        ownerService.addNewBook(username, book);

        return "redirect:/owner/" + username;

    }

    @PostMapping("owner/{username}/edit")
    @PreAuthorize("#username == authentication.name")
    public String EditBook(@ModelAttribute("book") Book book, @PathVariable("username") String username, @RequestParam("bookISBN") Long bookISBN) {

        ownerService.editBook(bookISBN, book);

        return "redirect:/owner/" + username;

    }

}
