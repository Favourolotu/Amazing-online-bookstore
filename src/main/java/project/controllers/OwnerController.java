package project.controllers;

import project.models.Book;
import project.models.Inventory;
import project.persistence.BookRepository;
import project.persistence.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class OwnerController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private InventoryRepository inventoryRepository;


    @GetMapping("/owner")
    public String getOwnerPage(Model model) {
        Iterable<Inventory> inventories = inventoryRepository.findAll();
        model.addAttribute("inventories", inventories);
        return "owner";
    }

    @GetMapping("owner/add")
    public String displayAddBookPage(@RequestParam("inventoryId") Long inventoryId, Model model) {
        model.addAttribute("inventoryId", inventoryId);
        model.addAttribute("book", new Book());
        return "add-book";
    }

    @PostMapping("owner/add")
    public String addNewBook(@ModelAttribute("book") Book book, @RequestParam("inventoryId") Long inventoryId) {
        Inventory inventory = inventoryRepository.findById(inventoryId).orElseThrow(() -> new RuntimeException("Error finding inventory with ID: " + inventoryId));

        book.setInventory(inventory);
        bookRepository.save(book);

        return "redirect:/owner";
    }

    @GetMapping("owner/edit")
    public String displayEditBookPage(@RequestParam("bookISBN") Long bookISBN, Model model) {
        Book book = bookRepository.findById(bookISBN).orElseThrow(() -> new RuntimeException("Error finding book with ID: " + bookISBN));
        model.addAttribute("book", book);
        return "edit-book";
    }

    @PostMapping("owner/edit")
    public String editBook(@RequestParam("bookISBN") Long bookISBN, @ModelAttribute Book editedBook) {
        Book book = bookRepository.findById(bookISBN).orElseThrow(() -> new RuntimeException("Error finding book with ID: " + bookISBN));
        book.setTitle(editedBook.getTitle());
        book.setAuthor(editedBook.getAuthor());
        book.setDescription(editedBook.getDescription());
        book.setPublisher(editedBook.getPublisher());
        bookRepository.save(book);

        return "redirect:/owner";
    }

}
