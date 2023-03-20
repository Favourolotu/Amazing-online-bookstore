package project.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.models.Book;
import project.models.ShoppingCart;
import project.models.User;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    /**
     * This method tests for adding to a shopping cart and persisting it
     */
    @Test
    public void savingUserTest(){
        User user = new User();
        user.setId(100L);
        Book book1 = new Book("1984", "George Orwell", "A dystopian novel set in a totalitarian society", "Secker & Warburg");
        Book book2 = new Book("The Catcher in the Rye", "J.D. Salinger", "A coming-of-age story about a teenage boy in New York City", "Little, Brown and Company");

        user.setPurchasedBook(book1);
        user.setPurchasedBook(book2);

        userRepository.save(user);

        User returnedUser = userRepository.findUserById(100L);

        assertTrue(returnedUser.getPurchasedBooks().contains(book1));
        assertTrue(returnedUser.getPurchasedBooks().contains(book2));
    }

}
