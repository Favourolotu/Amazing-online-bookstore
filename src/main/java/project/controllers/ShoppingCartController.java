package project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.models.Book;
import project.models.bookStub;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ShoppingCartController {

    @PostMapping("/makePurchase")
    public String makePurchase( Model model) {
        // TODO get books in shopping cart and add them to the user purchase list
        // TODO display the books purchased by the user

        // List of all purchases
        List<Book> bookList = getSampleBooks();

        model.addAttribute("books", bookList);
        return "userPurchases";
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
