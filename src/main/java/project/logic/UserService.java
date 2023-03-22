package project.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.Book;
import project.models.ShoppingCart;
import project.models.User;
import project.persistence.BookRepository;
import project.persistence.ShoppingCartRepository;
import project.persistence.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;



    /**
     * This method handles the response to the searchForBook endpoint
     * @param bookName
     * @return the view populated with the search results of books
     */
    public List<Book> searchForBooks(String bookName) {
        return bookRepository.findByTitle(bookName);
    }


    /**
     * This method handles the request to view the shopping cart
     * @return A view listing all the books a user has added to the cart
     */
    public List<Book> viewShoppingCart(String userName) {
        Optional<User> user_optional = userRepository.findUserByUsername(userName);

        if (user_optional.isEmpty()){
            throw new RuntimeException("Could not find user: " + userName);
        }

        return user_optional.get().getShoppingCart().getBookList();
    }

    /**
     * This method handles a user request to add a book to the shopping cart
     */
    public void addToShoppingCart(String userName, Book book) {
        // get specific book with id input by user into shopping cart
        Book book1 = bookRepository.findByTitle(book.getTitle()).get(0);
        book1.setStock(book.getStock());

        Optional<User> user_optional = userRepository.findUserByUsername(userName);

        if (user_optional.isEmpty()){
            throw new RuntimeException("Could not find user: " + userName);
        }
        User user = user_optional.get();
        ShoppingCart shoppingCart = user.getShoppingCart();
        shoppingCart.addBook(book1);
        shoppingCartRepository.save(shoppingCart);

    }

    /**
     * This method handles the user request to remove a book from the shopping cart
     */
    public void removeFromShoppingCart(String userName, Book book) {

        Book book1 = bookRepository.findByTitle(book.getTitle()).get(0);

        Optional<User> user_optional = userRepository.findUserByUsername(userName);

        if (user_optional.isEmpty()){
            throw new RuntimeException("Could not find user: " + userName);
        }
        User user = user_optional.get();
        ShoppingCart shoppingCart = user.getShoppingCart();
        shoppingCart.removeBook(book1);
        shoppingCartRepository.save(shoppingCart);

    }

    public List<Book> makePurchase(String username) {

        Optional<User> user_optional = userRepository.findUserByUsername(username);

        if (user_optional.isEmpty()){
            throw new RuntimeException("Could not find user: " + username);
        }
        User user = user_optional.get();
        ShoppingCart shoppingCart = user.getShoppingCart();

        for (Book book: shoppingCart.getBookList()){

            user.setPurchasedBook(book);
        }

        List<Book> bookList = user.getPurchasedBooks();

        shoppingCart.clear();
        userRepository.save(user);

        return bookList;
    }

}
