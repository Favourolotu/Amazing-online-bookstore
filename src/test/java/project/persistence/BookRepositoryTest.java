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
    public void testSetISBN(){
        book = new Book("percy jackson", "e.b. things", "greek gods", "rick");
        book.setISBN(1l);
        assertTrue(book.getISBN() == 1l);
    }
    @Test
    public void testGetISBN(){
        book = new Book("percy jackson", "e.b. things", "greek gods", "rick");
        book.setISBN(1l);
        assertEquals(1l, book.getISBN());
    }

    @Test
    public void testSetTitle(){
        book = new Book("percy jackson", "e.b. things", "greek gods", "rick");
        book.setTitle("lightening thief");
        assertTrue(book.getTitle() == "lightening thief");
    }
    @Test
    public void testGetTitle(){
        book = new Book("percy jackson", "e.b. things", "greek gods", "rick");
        assertEquals("percy jackson", book.getTitle());
    }

    @Test
    public void testSetAuthor(){
        book = new Book("percy jackson", "e.b. things", "greek gods", "rick");
        book.setAuthor("mike tyson");
        assertTrue(book.getAuthor() == "mike tyson");
    }
    @Test
    public void testGetAuthor(){
        book = new Book("percy jackson", "e.b. things", "greek gods", "rick");
        assertEquals("e.b. things", book.getAuthor());
    }

    @Test
    public void testSetDescription(){
        book = new Book("percy jackson", "e.b. things", "greek gods", "rick");
        book.setDescription("pen turns to sword");
        assertTrue(book.getDescription() == "pen turns to sword");
    }
    @Test
    public void testGetDescription(){
        book = new Book("percy jackson", "e.b. things", "greek gods", "rick");
        assertEquals("greek gods", book.getDescription());
    }

    @Test
    public void testSetPublisher(){
        book = new Book("percy jackson", "e.b. things", "greek gods", "rick");
        book.setPublisher("malcom x");
        assertTrue(book.getPublisher() == "malcom x");
    }
    @Test
    public void testGetPublisher(){
        book = new Book("percy jackson", "e.b. things", "greek gods", "rick");
        assertEquals("rick", book.getPublisher());
    }

    @Test
    public void testSetInventory(){
        Inventory inventory = new Inventory();
        book = new Book("percy jackson", "e.b. things", "greek gods", "rick");
        book.setInventory(inventory);
        assertTrue(book.getInventory() == inventory);
    }
    @Test
    public void testGetInventory(){
        Inventory inventory = new Inventory();
        book = new Book("percy jackson", "e.b. things", "greek gods", "rick");
        book.setInventory(inventory);
        assertEquals(inventory, book.getInventory());
    }

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
