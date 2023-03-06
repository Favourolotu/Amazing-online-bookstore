package project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        List<bookStub> bookList = new ArrayList<bookStub>();

        bookList.add(new bookStub(1L, "black dynamite"));
        bookList.add(new bookStub(2L, "ultimates"));
        bookList.add(new bookStub(3L, "Adventure time"));
        model.addAttribute("books", bookList);
        return "userPurchases";
    }
}
