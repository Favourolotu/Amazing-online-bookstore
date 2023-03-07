package project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import project.models.Book;
import project.persistence.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(BookstoreApplication.class, args);
    }
    @Bean
    public CommandLineRunner initDatabase(BookRepository bookRepository) {
        return (args) -> {

            Book book = new Book("lotr", "jr tolkien");
            bookRepository.save(book);

            Book book2 = new Book("lotr2", "jr tolkien");
            bookRepository.save(book2);

            Book book3 = new Book("lotr3", "jr tolkien");
            bookRepository.save(book3);

        };
    }



}
