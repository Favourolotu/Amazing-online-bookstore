package com.example.bookstore.entities;

import com.example.bookstore.book.Book;
import com.example.bookstore.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    private User mockUser;

    private Book book;

    @BeforeEach
    void setUp() {
        mockUser = new User();
        book = new Book("Test Title", "Test Author", "Test Description", "Test Publisher", "Test Image URL");
    }

    @Test
    void testBookConstructorAndGetters() {
        assertNotNull(book);
        assertEquals("Test Title", book.getTitle());
        assertEquals("Test Author", book.getAuthor());
        assertEquals("Test Description", book.getDescription());
        assertEquals("Test Publisher", book.getPublisher());
        assertEquals("Test Image URL", book.getImageUrl());
        assertEquals(10, book.getStock());
    }

    @Test
    void testBookSetters() {
        book.setTitle("Updated Title");
        book.setAuthor("Updated Author");
        book.setDescription("Updated Description");
        book.setPublisher("Updated Publisher");
        book.setImageUrl("Updated Image URL");
        book.setStock(20);

        assertEquals("Updated Title", book.getTitle());
        assertEquals("Updated Author", book.getAuthor());
        assertEquals("Updated Description", book.getDescription());
        assertEquals("Updated Publisher", book.getPublisher());
        assertEquals("Updated Image URL", book.getImageUrl());
        assertEquals(20, book.getStock());
    }

    @Test
    void testBookPurchasers() {
        List<User> purchasers = new ArrayList<>();
        purchasers.add(mockUser);
        book.setPurchasers(purchasers);

        assertEquals(1, book.getPurchasers().size());
        assertEquals(mockUser, book.getPurchasers().get(0));
    }

    @Test
    void testBookISBN() {
        book.setISBN(123456);

        assertEquals(123456, book.getISBN());
    }

    @Test
    void testBookNoArgsConstructor() {
        Book emptyBook = new Book();

        assertNotNull(emptyBook);
        assertNull(emptyBook.getTitle());
        assertNull(emptyBook.getAuthor());
        assertNull(emptyBook.getDescription());
        assertNull(emptyBook.getPublisher());
        assertNull(emptyBook.getImageUrl());
        assertEquals(0, emptyBook.getStock());
    }

}
