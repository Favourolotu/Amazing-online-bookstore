package com.example.bookstore.entities;

import com.example.bookstore.book.Book;
import com.example.bookstore.user.User;
import com.example.bookstore.cart.ShoppingCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartTest {
    private ShoppingCart shoppingCart;
    private User user;

    @BeforeEach
    void setUp() {
        shoppingCart = new ShoppingCart();
        user = new User();
        shoppingCart.setUser(user);
    }

    @Test
    void testGetId() {
        Integer id = 1;
        shoppingCart.setId(id);
        assertEquals(id, shoppingCart.getId());
    }

    @Test
    void testGetUser() {
        assertEquals(user, shoppingCart.getUser());
    }

    @Test
    void testGetItems() {
        Map<Book, Integer> items = new HashMap<>();
        Book book1 = new Book("Book 1", "Author 1", "Description 1", "Publisher 1", "image1.jpg");
        Book book2 = new Book("Book 2", "Author 2", "Description 2", "Publisher 2", "image2.jpg");
        items.put(book1, 1);
        items.put(book2, 2);
        shoppingCart.setItems(items);
        assertEquals(items, shoppingCart.getItems());
    }

    @Test
    void testSetItems() {
        Map<Book, Integer> items = new HashMap<>();
        Book book1 = new Book("Book 1", "Author 1", "Description 1", "Publisher 1", "image1.jpg");
        Book book2 = new Book("Book 2", "Author 2", "Description 2", "Publisher 2", "image2.jpg");
        items.put(book1, 1);
        items.put(book2, 2);
        shoppingCart.setItems(items);
        assertEquals(items, shoppingCart.getItems());
    }

}
