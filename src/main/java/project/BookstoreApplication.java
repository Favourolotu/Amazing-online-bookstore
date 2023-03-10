package project;

import project.models.Book;
import project.models.Inventory;
import project.persistence.BookRepository;
import project.persistence.InventoryRepository;
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
    public CommandLineRunner initDatabase(BookRepository bookRepository, InventoryRepository inventoryRepository) {
        return (args) -> {

            // Create an inventory
            Inventory inventory = new Inventory();

            // Create an inventory
            Inventory inventory2 = new Inventory();

            // Create an inventory
            Inventory inventory3 = new Inventory();


            books[0].setInventory(inventory);
            books[1].setInventory(inventory);
            books[2].setInventory(inventory);
            books[3].setInventory(inventory2);
            books[4].setInventory(inventory2);
            books[5].setInventory(inventory3);
            books[6].setInventory(inventory3);
            books[7].setInventory(inventory3);

            bookRepository.saveAll(Arrays.asList(books[0], books[1], books[2], books[3], books[4], books[5], books[6], books[7]));

            inventoryRepository.save(inventory);
            inventoryRepository.save(inventory2);
            inventoryRepository.save(inventory3);


        };
    }

    private static final Book[] books = new Book[] {
            new Book("The Great Gatsby", "F. Scott Fitzgerald", "A story about the American Dream in the 1920s", "Scribner"),
            new Book("To Kill a Mockingbird", "Harper Lee", "A story about racial injustice in the Deep South", "J. B. Lippincott & Co."),
            new Book("Pride and Prejudice", "Jane Austen", "A romantic comedy of manners in Georgian England", "T. Egerton, Whitehall"),
            new Book("1984", "George Orwell", "A dystopian novel set in a totalitarian society", "Secker & Warburg"),
            new Book("The Catcher in the Rye", "J.D. Salinger", "A coming-of-age story about a teenage boy in New York City", "Little, Brown and Company"),
            new Book("Animal Farm", "George Orwell", "A satirical allegory about Soviet totalitarianism", "Secker & Warburg"),
            new Book("Brave New World", "Aldous Huxley", "A dystopian novel set in a future society that is controlled by technology", "Chatto & Windus"),
            new Book("One Hundred Years of Solitude", "Gabriel Garcia Marquez", "A multi-generational family saga in a magical realist setting", "Editorial Sudamericana"),
            new Book("Lord of the Flies", "William Golding", "A novel about a group of boys stranded on a deserted island and their descent into savagery", "Faber and Faber"),
            new Book("Heart of Darkness", "Joseph Conrad", "A novella about the journey of an ivory transporter up the Congo River in the late 19th century", "Blackwood's Magazine"),
            new Book("The Adventures of Huckleberry Finn", "Mark Twain", "A story about a young boy's adventures on the Mississippi River in the mid-19th century", "Chatto & Windus"),
            new Book("The Grapes of Wrath", "John Steinbeck", "A novel about a family of tenant farmers who are forced to leave their home during the Great Depression", "The Viking Press"),
            new Book("The Sun Also Rises", "Ernest Hemingway", "A novel about a group of American and British expatriates who travel from Paris to the Festival of San Ferm??n in Pamplona, Spain", "Scribner's"),
            new Book("The Bell Jar", "Sylvia Plath", "A semi-autobiographical novel about a young woman's descent into mental illness", "Heinemann"),
            new Book("The Picture of Dorian Gray", "Oscar Wilde", "A novel about a man who remains youthful while his portrait ages and bears the scars of his sins", "Ward, Lock, and Company")
    };


}
