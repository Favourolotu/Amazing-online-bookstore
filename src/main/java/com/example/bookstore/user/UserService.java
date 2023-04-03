package com.example.bookstore.user;

import com.example.bookstore.book.Book;
import com.example.bookstore.book.BookRepository;
import com.example.bookstore.cart.ShoppingCart;
import com.example.bookstore.cart.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final BookRepository bookRepository;

    public ShoppingCart getShoppingCart(String username) {
        return shoppingCartRepository.findByUser_Username(username)
                .orElseThrow(() -> new RuntimeException("Resource not found"));
    }

    public void addToCart(String username, Integer bookISBN) {
        ShoppingCart cart = getShoppingCart(username);
        cart.getItems().put(bookRepository.findById(bookISBN).orElseThrow(() -> new RuntimeException("book not found")), 1);
        shoppingCartRepository.save(cart);
    }

    public void checkout(String username) {
        ShoppingCart cart = getShoppingCart(username);
        for (Map.Entry<Book, Integer> entry : cart.getItems().entrySet()) {
            entry.getKey().setStock(entry.getKey().getStock() - entry.getValue());
            bookRepository.save(entry.getKey());
        }
        cart.getItems().clear();
        shoppingCartRepository.save(cart);
    }
}
