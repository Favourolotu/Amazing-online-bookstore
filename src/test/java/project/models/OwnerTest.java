package project.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class OwnerTest {
    private Book book1;
    private Book book2;
    private Owner owner;

    @Test
    public void testSetId(){
        owner = new Owner();
        owner.setId(1L);
        assertEquals(1L, (long) owner.getId());
    }

    @Test
    public void testGetId(){
        owner = new Owner();
        owner.setId(2L);
        assertEquals(2L, owner.getId());
    }

    @Test
    public void testSetBooks(){
        owner = new Owner();
        book1 = new Book("percy jackson", "e.b. things", "greek gods", "rick");
        book2 = new Book("game of thrones", "j.r. martin", "dragons and shit", "dean");
        List<Book> books = new LinkedList<>();
        books.add(book1);
        books.add(book2);
        owner.setBooks(books);
        assertEquals(2, owner.getBooks().size());
    }

    @Test
    public void testGetBooks(){
        owner = new Owner();
        book1 = new Book("percy jackson", "e.b. things", "greek gods", "rick");
        book2 = new Book("game of thrones", "j.r. martin", "dragons and shit", "dean");
        List<Book> books = new LinkedList<>();
        books.add(book1);
        books.add(book2);
        owner.setBooks(books);
        assertEquals(2, owner.getBooks().size());
    }

}
