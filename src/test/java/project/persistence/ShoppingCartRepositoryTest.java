package project.persistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.models.Book;
import project.models.ShoppingCart;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ShoppingCartRepositoryTest {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @Test
    /**
     * This method tests for adding to a shopping cart and persisting it
     */
    public void testAddToShoppingCart(){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(100L);
        Book book1 = new Book("1984", "George Orwell", "A dystopian novel set in a totalitarian society", "Secker & Warburg");
        Book book2 = new Book("The Catcher in the Rye", "J.D. Salinger", "A coming-of-age story about a teenage boy in New York City", "Little, Brown and Company");

        shoppingCart.addBook(book1);
        shoppingCart.addBook(book2);

        shoppingCartRepository.save(shoppingCart);

        ShoppingCart returned = shoppingCartRepository.findShoppingCartById(100L);

        assertTrue(returned.getBookList().contains(book1));
        assertTrue(returned.getBookList().contains(book2));
    }

    @Test
    /**
     * This method tests for removing from a shopping cart and persisting it
     */
    public void testRemoveFromShoppingCart(){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(100L);
        Book book1 = new Book("1984", "George Orwell", "A dystopian novel set in a totalitarian society", "Secker & Warburg");
        Book book2 = new Book("The Catcher in the Rye", "J.D. Salinger", "A coming-of-age story about a teenage boy in New York City", "Little, Brown and Company");

        shoppingCart.addBook(book1);
        shoppingCart.addBook(book2);

        shoppingCartRepository.save(shoppingCart);

        ShoppingCart returned = shoppingCartRepository.findShoppingCartById(100L);

        assertTrue(returned.getBookList().contains(book1));
        assertTrue(returned.getBookList().contains(book2));

        returned.clear();

        shoppingCartRepository.save(returned);

        ShoppingCart returnedCart = shoppingCartRepository.findShoppingCartById(100L);

        assertFalse(returnedCart.getBookList().contains(book1));
        assertFalse(returnedCart.getBookList().contains(book2));
    }
}
