package com.example.bookstore;

import com.example.bookstore.auth.AuthenticationService;
import com.example.bookstore.auth.RegisterRequest;
import com.example.bookstore.book.Book;
import com.example.bookstore.book.BookService;
import com.example.bookstore.user.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class BookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }


    @Bean
    public CommandLineRunner initDatabase(AuthenticationService authService, BookService bookService) {
        return args -> {
            authService.register(new RegisterRequest("chase", "pass", Role.USER));
            authService.register(new RegisterRequest("owner", "pass", Role.OWNER));

            Arrays.stream(books).forEach(bookService::addBook);
        };
    }

    private static final Book[] books = new Book[] {
            new Book("The Great Gatsby", "F. Scott Fitzgerald", "A story about the American Dream in the 1920s", "Scribner", "/imgs/great-gatsby.jpg"),
            new Book("To Kill a Mockingbird", "Harper Lee", "A story about racial injustice in the Deep South", "J. B. Lippincott & Co.", "/imgs/to-kill-a-mockingbird.jpg"),
            new Book("Pride and Prejudice", "Jane Austen", "A romantic comedy of manners in Georgian England", "T. Egerton, Whitehall", "/imgs/pride-and-prejudice.jpg"),
            new Book("1984", "George Orwell", "A dystopian novel set in a totalitarian society", "Secker & Warburg", "/imgs/1984.jpg"),
            new Book("The Catcher in the Rye", "J.D. Salinger", "A coming-of-age story about a teenage boy in New York City", "Little, Brown and Company", "/imgs/catcher-in-the-rye.jpg"),
            new Book("Animal Farm", "George Orwell", "A satirical allegory about Soviet totalitarianism", "Secker & Warburg", "/imgs/animal-farm.jpg"),
            new Book("Brave New World", "Aldous Huxley", "A dystopian novel set in a future society that is controlled by technology", "Chatto & Windus", "/imgs/brave-new-world.jpg"),
            new Book("One Hundred Years of Solitude", "Gabriel Garcia Marquez", "A multi-generational family saga in a magical realist setting", "Editorial Sudamericana", "/imgs/one-hundred-years-of-solitude.jpg"),
            new Book("Lord of the Flies", "William Golding", "A novel about a group of boys stranded on a deserted island and their descent into savagery", "Faber and Faber", "/imgs/lord-of-the-flies.jpg"),
            new Book("Heart of Darkness", "Joseph Conrad", "A novella about the journey of an ivory transporter up the Congo River in the late 19th century", "Blackwood's Magazine", "/imgs/heart-of-darkness.jpg"),
            new Book("The Adventures of Huckleberry Finn", "Mark Twain", "A story about a young boy's adventures on the Mississippi River in the mid-19th century", "Chatto & Windus", "/imgs/adventures-of-huckleberry-finn.jpg"),
            new Book("The Grapes of Wrath", "John Steinbeck", "A novel about a family of tenant farmers who are forced to leave their home during the Great Depression", "The Viking Press", "/imgs/grapes-of-wrath.jpg"),
            new Book("The Sun Also Rises", "Ernest Hemingway", "A novel about a group of American and British expatriates who travel from Paris to the Festival of San Fermín in Pamplona, Spain", "Scribner's", "/imgs/sun-also-rises.jpg"),
            new Book("The Bell Jar", "Sylvia Plath", "A semi-autobiographical novel about a young woman's descent into mental illness", "Heinemann", "/imgs/bell-jar.jpg"),
            new Book("The Picture of Dorian Gray", "Oscar Wilde", "A novel about a man who remains youthful while his portrait ages and bears the scars of his sins", "Ward, Lock, and Company", "/imgs/picture-of-dorian-gray.jpg")


    };

}
