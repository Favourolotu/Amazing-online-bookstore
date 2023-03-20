package project.models;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class ShoppingCartTest {


    /**
     * This method tests the ID variable's getter and setter
     * from the ShoppingCart class
     */
    @Test
    public void getSetIdTest() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Long id = 1L;
        shoppingCart.setId(id);

        assertTrue(shoppingCart.getId().equals(id));
    }


    /**
     * This method tests the User variable's getter and setter
     * from the ShoppingCart class
     */
    @Test
    public void setGetUserTest() {
        ShoppingCart shoppingCart = new ShoppingCart();

        assertTrue(shoppingCart.getUser() == null);

        User user = new User();
        shoppingCart.setUser(user);

        assertTrue(shoppingCart.getUser().equals(user));
    }


    /**
     * This method tests the  book list variable's getter and setter
     * from the ShoppingCart class
     */
    @Test
    public void setGetBookListTest() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Book book1 = new Book("1984", "George Orwell", "A dystopian novel set in a totalitarian society", "Secker & Warburg");
        Book book2 = new Book("The Catcher in the Rye", "J.D. Salinger", "A coming-of-age story about a teenage boy in New York City", "Little, Brown and Company");

        shoppingCart.addBook(book1);
        shoppingCart.addBook(book2);

        assertTrue(shoppingCart.getBookList().contains(book1));
        assertTrue(shoppingCart.getBookList().contains(book2));
    }


    /**
     * This method tests the ability to remove a book object from the ShoppingCart class
     * Using the setter for the bookList variable for set-up
     */
    @Test
    public void removeBookTest() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Book book1 = new Book("1984", "George Orwell", "A dystopian novel set in a totalitarian society", "Secker & Warburg");
        Book book2 = new Book("The Catcher in the Rye", "J.D. Salinger", "A coming-of-age story about a teenage boy in New York City", "Little, Brown and Company");

        List<Book> bookList = new ArrayList<Book>();
        bookList.add(book1);
        bookList.add(book2);
        shoppingCart.setBookList(bookList);

        shoppingCart.removeBook(book1);
        assertFalse(shoppingCart.getBookList().contains(book1));
        assertTrue(shoppingCart.getBookList().contains(book2));

    }

    /**
     * This method tests the ability clear the ShoppingCart class
     */
    @Test
    public void clearShoppingCartTest() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Book book1 = new Book("1984", "George Orwell", "A dystopian novel set in a totalitarian society", "Secker & Warburg");
        Book book2 = new Book("The Catcher in the Rye", "J.D. Salinger", "A coming-of-age story about a teenage boy in New York City", "Little, Brown and Company");

        List<Book> bookList = new ArrayList<Book>();
        bookList.add(book1);
        bookList.add(book2);
        shoppingCart.setBookList(bookList);
        assertTrue(shoppingCart.getBookList().contains(book1));

        shoppingCart.clear();
        assertFalse(shoppingCart.getBookList().contains(book1));
        assertFalse(shoppingCart.getBookList().contains(book2));

    }
}
