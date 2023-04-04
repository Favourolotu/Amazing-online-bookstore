package com.example.bookstore.user;

import com.example.bookstore.book.Book;
import com.example.bookstore.book.BookRepository;
import com.example.bookstore.cart.ShoppingCart;
import com.example.bookstore.cart.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public void addToCart(String username, Integer bookISBN, Integer quantity) {
        ShoppingCart cart = getShoppingCart(username);
        cart.getItems().merge(bookRepository.findById(bookISBN).orElseThrow(() -> new RuntimeException("book not found")), quantity, Integer::sum);
        shoppingCartRepository.save(cart);
    }

    public void removeFromCart(String username, Integer bookISBN) {
        ShoppingCart cart = getShoppingCart(username);
        cart.getItems().remove(bookRepository.findById(bookISBN).orElseThrow(() -> new RuntimeException("book not found")));
        shoppingCartRepository.save(cart);
    }

    public void checkout(String username) {
        ShoppingCart cart = getShoppingCart(username);
        User user = this.userRepository.findUserByUsername(username).orElseThrow(() -> new RuntimeException("user not found"));
        for (Map.Entry<Book, Integer> cartItem : cart.getItems().entrySet()) {

            if (cartItem.getValue() <= cartItem.getKey().getStock()) {
                cartItem.getKey().setStock(cartItem.getKey().getStock() - cartItem.getValue());
            } else {
                //if amount in cart is greater than how much is in stock, buy all available
                cartItem.getKey().setStock(0);
            }
            //add this book to users list of purchased books
            user.getPurchasedBooks().add(cartItem.getKey());
            bookRepository.save(cartItem.getKey());
        }
        cart.getItems().clear();
        shoppingCartRepository.save(cart);
    }

    public Iterable<Book> getRecommendations(String username) {

        List<Book> recommendedBooks = new ArrayList<>();
        User currentUser = this.userRepository.findUserByUsername(username).orElseThrow(() -> new RuntimeException("user not found")); // Retrieve the current user
        Set<Book> currentUserPurchasedBooks = new HashSet<>(currentUser.getPurchasedBooks()); // Get purchased books of current user

        for (User user : this.userRepository.findAll()) {
            if (user.equals(currentUser)) {
                continue; // Skip comparing the current user to itself
            }

            Set<Book> userPurchasedBooks = new HashSet<>(user.getPurchasedBooks()); // Get purchased books of the compared user
            // Calculate Jaccard distance
            Set<Book> union = new HashSet<>(currentUserPurchasedBooks);
            union.addAll(userPurchasedBooks);
            Set<Book> intersection = new HashSet<>(currentUserPurchasedBooks);
            intersection.retainAll(userPurchasedBooks);
            double jaccardDistance = (double) intersection.size() / (double) union.size();

            if (jaccardDistance > 0.5) {
                for (Book book : userPurchasedBooks) {
                    if (!currentUserPurchasedBooks.contains(book)) {
                        recommendedBooks.add(book);
                    }
                }
            }
        }
        return recommendedBooks;
    }
}
