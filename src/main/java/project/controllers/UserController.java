package project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.models.bookStub;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    //@Autowired
    //private UserRepository userRepository;

    //@Autowired
    //private ShoppingCartRepository shoppingCartRepository;

    @GetMapping("/userDefaultPage")
    public String defaultDisplay(Model model) {
        // model.addAttribute("addressBook", new AddressBook());
        // model.addAttribute("buddy", new BuddyInfo());
        return "userDefaultPage";
    }

    @PostMapping("/logout")
    public String logoutUser(Model model) {
        // model.addAttribute("addressBook", new AddressBook());
        // model.addAttribute("buddy", new BuddyInfo());
        return "indexStub";
    }

    @PostMapping("/searchForBook")
    public String searchForBooks(@RequestParam("bookName") String bookName, Model model) {
        // TODO Search for books by name in the inventory repository and display results
        List<bookStub> bookList = new ArrayList<bookStub>();
        bookList.add(new bookStub(1L, "willow tree"));
        bookList.add(new bookStub(2L, "Boondocks"));
        bookList.add(new bookStub(3L, "Adventure time"));
        bookList.add(new bookStub(4L, bookName));
        model.addAttribute("books", bookList);
        return "listOfBooksDisplay";
    }

    @PostMapping("/browseInventory")
    public String browseInventory( Model model) {
        // TODO get list of all books from the inventory and display
        List<bookStub> bookList = new ArrayList<bookStub>();
        bookList.add(new bookStub(1L, "willow tree"));
        bookList.add(new bookStub(2L, "Boondocks"));
        bookList.add(new bookStub(3L, "Adventure time"));
        model.addAttribute("books", bookList);
        return "listOfBooksDisplay";
    }

    @PostMapping("/viewShoppingCart")
    public String viewShoppingCart( Model model) {
        // TODO get list of all books in the users shopping cart and display
        List<bookStub> bookList = new ArrayList<bookStub>();
        bookList.add(new bookStub(1L, "willow tree"));
        bookList.add(new bookStub(2L, "Boondocks"));
        bookList.add(new bookStub(3L, "Adventure time"));
        model.addAttribute("books", bookList);
        return "viewShoppingCart";
    }

    @PostMapping("/addToShoppingCart")
    public String addToShoppingCart(@RequestParam("bookID") Long bookID, Model model) {
        // TODO get specific book with id input by user into shopping cart
        boolean bookIdIsValid = true;
        String message = "";
        bookStub bookToAdd = new bookStub(bookID, "Adventure time");
        if (bookIdIsValid) {
            message = "Book successfully added to cart";
        } else {
            message = "Invalid book ID entered";
        }
        model.addAttribute("message", message);
        model.addAttribute("book", bookToAdd);
        return "addedToShoppingCart";
    }
}
