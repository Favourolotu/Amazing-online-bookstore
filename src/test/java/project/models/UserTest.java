package project.models;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class UserTest {


    /**
     * This method tests the username variable's getter and setter
     * from the user class
     */
    @Test
    public void getSetUserNameTest() {
        User user = new User();
        String name = "Ryan";
        user.setUsername(name);

        assertTrue(user.getUsername().equals(name));
    }


    /**
     * This method tests the ID variable's getter and setter
     * from the user class
     */
    @Test
    public void getSetIdTest() {
        User user = new User();
        Long id = 1L;
        user.setId(id);

        assertTrue(user.getId().equals(id));
    }


    /**
     * This method tests the Shopping cart variable's getter and setter
     * from the user class
     */
    @Test
    public void setGetShoppingCartTest() {
        User user = new User();
        assertTrue(user.getShoppingCart() == null);

        ShoppingCart shoppingCart = new ShoppingCart();
        user.setShoppingCart(shoppingCart);

        assertTrue(user.getShoppingCart().equals(shoppingCart));
    }


    /**
     * This method tests the Purchased books variable's getter and setter
     * from the user class
     */
    @Test
    public void setGetPurchasedBooksTest() {
        User user = new User();
        Book book1 = new Book("1984", "George Orwell", "A dystopian novel set in a totalitarian society", "Secker & Warburg");
        Book book2 = new Book("The Catcher in the Rye", "J.D. Salinger", "A coming-of-age story about a teenage boy in New York City", "Little, Brown and Company");

        user.setPurchasedBook(book1);
        user.setPurchasedBook(book2);


        assertTrue(user.getPurchasedBooks().contains(book1));
    }
}