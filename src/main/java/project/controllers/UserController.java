package project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.models.Book;
import project.models.bookStub;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private List books = getSampleBooks();

    /**
     * This method handles the response to the user default page endpoint
     * @param model
     * @return default page view
     */
    @GetMapping("/userDefaultPage")
    public String defaultDisplay(Model model) {
        // TODO get all the available inventory
        model.addAttribute("books", getSampleBooks());
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
        // TODO Search for books by name in the inventory repository and display results

        List<Book> books = getSampleBooks();
        Book edit = books.get(1);
        edit.setName(bookName);
        books.add(edit);
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

        model.addAttribute("books", books);
        return "viewShoppingCart";
    }

    /**
     * This method handles a user request to add a book to the shopping cart
     * @param bookID
     * @return Returns the shopping cart view with the new book added to it
     */
    @PostMapping("/addToShoppingCart/{bookID}")
    public String addToShoppingCart(@PathVariable Long bookID) {
        // TODO get specific book with id input by user into shopping cart

        Book sample4 = new Book();
        sample4.setIsbn(bookID);
        sample4.setName("Xiv");
        sample4.setAuthor("test");
        sample4.setDescription("A book written by test");
        sample4.setPublisher("SYSC 4806");
        books.add(sample4);
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
        books.remove((int)(long)bookID - 1);
        return "redirect:/viewShoppingCart";
    }

    private List<Book> getSampleBooks(){
        List<Book> bookList = new ArrayList<Book>();

        Book sample1 = new Book();
        sample1.setIsbn(1L);
        sample1.setName("Xi");
        sample1.setAuthor("Chase");
        sample1.setDescription("A book written by chase");
        sample1.setPublisher("SYSC 4806");

        Book sample2 = new Book();
        sample2.setIsbn(2L);
        sample2.setName("Xii");
        sample2.setAuthor("Favour");
        sample2.setDescription("A book written by Favour");
        sample2.setPublisher("SYSC 4806");

        Book sample3 = new Book();
        sample3.setIsbn(3L);
        sample3.setName("Xiii");
        sample3.setAuthor("Joseph");
        sample3.setDescription("A book written by Joseph");
        sample3.setPublisher("SYSC 4806");

        bookList.add(sample1);
        bookList.add(sample2);
        bookList.add(sample3);
        return bookList;
    }
}
