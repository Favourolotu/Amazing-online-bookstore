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
import project.persistence.BookRepository;
import project.persistence.InventoryRepository;
import project.persistence.ShoppingCartRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {


    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private InventoryRepository inventoryRepository;



    /**
     * This method handles the response to the user default page endpoint
     * @param model
     * @return default page view
     */
    @GetMapping("/userDefaultPage")
    public String defaultDisplay(Model model) {
        List<Book> books = (List<Book>) bookRepository.findAll();
        model.addAttribute("books", books);
        return "userDefaultPage";
    }

    /**
     * This method handle the response to the user deciding to log out
     * @param model
     * @return the initial sign in view
     */
    @PostMapping("/logout")
    public String logoutUser(Model model) {
        return "indexStub";
    }

    /**
     * This method handles the response to the searchForBook endpoint
     * @param bookName
     * @param model
     * @return the view populated with the search results of books
     */
    @PostMapping("/searchForBook")
    public String searchForBooks(@RequestParam("bookName") String bookName, Model model) {
        List<Book> books = bookRepository.findByName(bookName);
        model.addAttribute("books", books);
        return "listOfBooksDisplay";
    }

    /**
     * This method handles the request to view the shopping cart
     * @param model
     * @return A view listing all the books a user has added to the cart
     */
    @GetMapping("/viewShoppingCart")
    public String viewShoppingCart( Model model) {
        // TODO get list of all books in the users shopping cart and display

        //model.addAttribute("books", );
        return "viewShoppingCart";
    }

    /**
     * This method handles a user request to add a book to the shopping cart
     * @param bookID
     * @return Returns the shopping cart view with the new book added to it
     */
    @PostMapping("/addToShoppingCart/{bookID}")
    public String addToShoppingCart(@PathVariable Long bookID) {
        // get specific book with id input by user into shopping cart
        Optional<Book> bookToAdd = bookRepository.findById(bookID);
        if (shoppingCartRepository.findById(1L) == null){
            shoppingCartRepository.save(new ShoppingCart());
        }
        //ShoppingCart shoppingCart = shoppingCartRepository.findById(1L) ;
        //shoppingCart.addBook();


        return "redirect:/viewShoppingCart";
    }

    /**
     * This method handles the user request to remove a book from the shopping cart
     * @param bookID
     * @return The view of the shopping cart with the book removed
     */
    @PostMapping("/removeFromShoppingCart/{bookID}")
    public String removeFromShoppingCart(@PathVariable Long bookID) {
        // TODO remove specific book with id input by user into shopping cart
        return "redirect:/viewShoppingCart";
    }

    @PostMapping("/makePurchase")
    public String makePurchase( Model model) {
        // TODO get books in shopping cart and add them to the user purchase list
        // TODO display the books purchased by the user

        // List of all purchases

        //model.addAttribute("books", bookList);
        return "userPurchases";
    }
}
