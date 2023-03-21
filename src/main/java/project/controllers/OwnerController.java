package project.controllers;

import project.logic.OwnerService;
import project.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping("/owner/{ownerId}")
    public String getOwnerPage(@PathVariable Long ownerId, Model model) {

            // The user is logged in and accessing their own owner page
            model.addAttribute("owner", ownerService.getOwnerById(ownerId));
            return "owner";

    }

    @GetMapping("/owner/{ownerId}/add")
    public String getAddBookPage(@PathVariable Long ownerId, Model model) {

            // The user is logged in and accessing their own owner page
            model.addAttribute("owner", ownerService.getOwnerById(ownerId));
            model.addAttribute("book", new Book());
            return "add-book";

    }

    @GetMapping("/owner/{ownerId}/edit")
    public String getEditBookPage(@PathVariable Long ownerId, @RequestParam("bookISBN") Long bookISBN, Model model) {

            // The user is logged in and accessing their own owner page
            model.addAttribute("owner", ownerService.getOwnerById(ownerId));
            model.addAttribute("book", ownerService.getBookById(bookISBN));
            return "edit-book";

    }


    @PostMapping("owner/{ownerId}/add")
    public String addNewBook(@ModelAttribute("book") Book book, @PathVariable("ownerId") Long ownerId) {

            ownerService.addNewBook(ownerId, book);

            return "redirect:/owner/" + ownerId;

    }


    @PostMapping("owner/{ownerId}/edit")
    public String EditBook(@ModelAttribute("book") Book book, @PathVariable("ownerId") Long ownerId, @RequestParam("bookISBN") Long bookISBN) {

            ownerService.editBook(bookISBN, book);

            return "redirect:/owner/" + ownerId;

    }

}
