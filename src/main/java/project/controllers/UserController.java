package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.logic.UserService;
import project.models.Book;
import project.persistence.BookRepository;

@Controller
public class UserController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/user/{username}")
    @PreAuthorize("#username == authentication.name")
    public String getUserPage(@PathVariable String username, Model model) {
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("username", username);
        model.addAttribute("books", books);
        return "user";
    }



    @GetMapping("/user/{username}/search")
    @PreAuthorize("#username == authentication.name")
    public String searchForBooks(@PathVariable String username, @RequestParam("bookName") String bookName, Model model) {
        model.addAttribute("username", username);
        model.addAttribute("books", userService.searchForBooks(bookName));
        return "list-books";
    }


    @GetMapping("/user/{username}/viewCart")
    @PreAuthorize("#username == authentication.name")
    public String viewShoppingCart(@PathVariable String username, Model model) {

        model.addAttribute("username", username);
        model.addAttribute("books", userService.viewShoppingCart(username));
        return "view-cart";
    }


    @PostMapping("/user/{username}/addToCart")
    @PreAuthorize("#username == authentication.name")
    public String addToShoppingCart(@PathVariable("username") String username,
                                    Book book) {
        userService.addToShoppingCart(username, book);
        return "redirect:/user/" + username + "/viewCart";
    }


    @PostMapping("/user/{username}/removeBook")
    @PreAuthorize("#username == authentication.name")
    public String removeFromShoppingCart(@PathVariable("username") String username,
                                         Book book) {
        userService.removeFromShoppingCart(username, book);
        return "redirect:/user/" + username + "/viewCart";
    }

    @PostMapping("/user/{username}/makePurchase")
    @PreAuthorize("#username == authentication.name")
    public String makePurchase(@PathVariable("username") String username,
                               Model model){

        model.addAttribute("books", userService.makePurchase(username));
        return "user-purchases";
    }


}
