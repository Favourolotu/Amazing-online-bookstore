package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

        model.addAttribute("books", userService.viewShoppingCart(username));
        return "view-cart";
    }

//    /**
//     * This method handles a user request to add a book to the shopping cart
//     * @param ISBN
//     * @return Returns the shopping cart view with the new book added to it
//     */
//    @PostMapping("/addToShoppingCart/{ISBN}")
//    public String addToShoppingCart(@PathVariable Long ISBN) {
//        // get specific book with id input by user into shopping cart
//        Book book = bookRepository.findByISBN(ISBN);
//
//        User user = userRepository.findUserById(1L);
//        ShoppingCart shoppingCart = user.getShoppingCart();
//        shoppingCart.addBook(book);
//        userRepository.save(user);
//
//        return "redirect:/viewShoppingCart";
//    }
//
//    /**
//     * This method handles the user request to remove a book from the shopping cart
//     * @param ISBN
//     * @return The view of the shopping cart with the book removed
//     */
//    @PostMapping("/removeFromShoppingCart/{ISBN}")
//    public String removeFromShoppingCart(@PathVariable Long ISBN) {
//        Book book = bookRepository.findByISBN(ISBN);
//
//        User user = userRepository.findUserById(1L);
//        ShoppingCart shoppingCart = user.getShoppingCart();
//        shoppingCart.removeBook(book);
//        userRepository.save(user);
//
//        return "redirect:/viewShoppingCart";
//    }
//
//    @PostMapping("/makePurchase")
//    public String makePurchase( Model model) {
//
//        User user = userRepository.findUserById(1L);
//        ShoppingCart shoppingCart = user.getShoppingCart();
//
//        for (Book book: shoppingCart.getBookList()){
//            user.setPurchasedBook(book);
//        }
//
//        List<Book> bookList = user.getPurchasedBooks();
//
//        shoppingCart.clear();
//        userRepository.save(user);
//
//        model.addAttribute("books", bookList);
//        return "user-purchases";
//    }
//

}
