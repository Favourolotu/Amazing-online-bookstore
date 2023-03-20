package project.persistence;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import project.models.Book;
import project.models.Inventory;

import java.util.*;

@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;
    private Book book;
    @Test
    public void testSaveToRepo(){
        bookRepository.deleteAll();
        book = new Book("percy jackson", "e.b. things", "greek gods", "rick");
        Book book2 = new Book("game of thrones", "j.r. martin", "dragons and shit", "dean");
        bookRepository.save(book);
        bookRepository.save(book2);
        assertEquals( 2, bookRepository.count());
    }





}
