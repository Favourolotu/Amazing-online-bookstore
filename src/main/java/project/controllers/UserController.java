package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.models.Book;
import project.models.Inventory;
import project.models.ShoppingCart;
import project.models.User;
import project.persistence.BookRepository;
import project.persistence.InventoryRepository;
import project.persistence.ShoppingCartRepository;
import project.persistence.UserRepository;

import java.util.List;

@Controller
public class UserController {


    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private UserRepository userRepository;


    /**
     * This method handles the response to the user default page endpoint
     * @param model
     * @return default page view
     */
    @GetMapping("/userDefaultPage")
    public String defaultDisplay(Model model) {
        Iterable<Inventory> inventories = inventoryRepository.findAll();
        // One shopping cart per user
        // User user = new User();
        // user.setId(1L);
        // user.setShoppingCart(new ShoppingCart());
        // userRepository.save(user);
        shoppingCartRepository.save(new ShoppingCart());
        model.addAttribute("inventories", inventories);
        return "user-default-page";
    }

    /**
     * This method handle the response to the user deciding to log out
     * @param model
     * @return the initial sign in view
     */
    @PostMapping("/logout")
    public String logoutUser(Model model) {
        return "login-page";
    }

    /**
     * This method handles the response to the searchForBook endpoint
     * @param bookName
     * @param model
     * @return the view populated with the search results of books
     */
    @PostMapping("/searchForBook")
    public String searchForBooks(@RequestParam("bookName") String bookName, Model model) {
        List<Book> books = bookRepository.findByTitle(bookName);
        model.addAttribute("books", books);
        return "list-of-books-display";
    }

    /**
     * This method handles the request to view the shopping cart
     * @param model
     * @return A view listing all the books a user has added to the cart
     */
    @GetMapping("/viewShoppingCart")
    public String viewShoppingCart( Model model) {

        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartById(1L) ;
        model.addAttribute("books", shoppingCart.getBookList());
        return "view-shopping-cart";
    }

    /**
     * This method handles a user request to add a book to the shopping cart
     * @param ISBN
     * @return Returns the shopping cart view with the new book added to it
     */
    @PostMapping("/addToShoppingCart/{ISBN}")
    public String addToShoppingCart(@PathVariable Long ISBN) {
        // get specific book with id input by user into shopping cart
        Book book = bookRepository.findByISBN(ISBN);

        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartById(1L);
        shoppingCart.addBook(book);
        shoppingCartRepository.save(shoppingCart);

        return "redirect:/view-shopping-cart";
    }

    /**
     * This method handles the user request to remove a book from the shopping cart
     * @param ISBN
     * @return The view of the shopping cart with the book removed
     */
    @PostMapping("/removeFromShoppingCart/{ISBN}")
    public String removeFromShoppingCart(@PathVariable Long ISBN) {
        Book book = bookRepository.findByISBN(ISBN);

        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartById(1L);
        shoppingCart.removeBook(book);
        shoppingCartRepository.save(shoppingCart);

        return "redirect:/view-shopping-cart";
    }

    @PostMapping("/makePurchase")
    public String makePurchase( Model model) {
        // TODO get books in shopping cart and add them to the user purchase list

        // User user = userRepository.findById(1);
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartById(1L);
        List<Book> bookList = shoppingCart.getBookList();
        shoppingCart.clear();
        shoppingCartRepository.save(shoppingCart);

        model.addAttribute("books", bookList);
        return "user-purchases";
    }
}
