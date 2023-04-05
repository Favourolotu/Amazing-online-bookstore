package com.example.bookstore.entities;

import com.example.bookstore.book.Book;
import com.example.bookstore.cart.ShoppingCart;
import com.example.bookstore.user.Role;
import com.example.bookstore.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User user;

    @Mock
    private ShoppingCart shoppingCart;

    @BeforeEach
    public void setUp() {
        user = User.builder()
                .id(1)
                .username("user1")
                .password("password1")
                .role(Role.USER)
                .shoppingCart(shoppingCart)
                .build();
    }

    @Test
    public void testGetId() {
        assertEquals(1, user.getId());
    }

    @Test
    public void testGetUsername() {
        assertEquals("user1", user.getUsername());
    }

    @Test
    public void testGetPassword() {
        assertEquals("password1", user.getPassword());
    }

    @Test
    public void testGetAuthorities() {
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        assertEquals(1, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority("USER")));
    }

    @Test
    public void testGetShoppingCart() {
        assertEquals(shoppingCart, user.getShoppingCart());
    }

    @Test
    public void testSetShoppingCart() {
        ShoppingCart newCart = new ShoppingCart();
        user.setShoppingCart(newCart);
        assertEquals(newCart, user.getShoppingCart());
    }

    @Test
    public void testGetPurchasedBooks() {
        Set<Book> purchasedBooks = new HashSet<>();
        Book book1 = new Book("Book 1", "Author 1", "Description 1", "Publisher 1", "Image URL 1");
        Book book2 = new Book("Book 2", "Author 2", "Description 2", "Publisher 2", "Image URL 2");
        purchasedBooks.add(book1);
        purchasedBooks.add(book2);
        user.setPurchasedBooks(purchasedBooks);
        assertEquals(purchasedBooks, user.getPurchasedBooks());
    }

    @Test
    public void testIsAccountNonExpired() {
        assertTrue(user.isAccountNonExpired());
    }

    @Test
    public void testIsAccountNonLocked() {
        assertTrue(user.isAccountNonLocked());
    }

    @Test
    public void testIsCredentialsNonExpired() {
        assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    public void testIsEnabled() {
        assertTrue(user.isEnabled());
    }










}
