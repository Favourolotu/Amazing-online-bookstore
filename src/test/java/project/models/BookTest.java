package project.models;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class BookTest {
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
        Owner owner = new Owner();
        book = new Book("percy jackson", "e.b. things", "greek gods", "rick");
        book.setOwner(owner);
        assertTrue(book.getOwner() == owner);
    }
    @Test
    public void testGetInventory(){
        Owner owner = new Owner();
        book = new Book("percy jackson", "e.b. things", "greek gods", "rick");
        book.setOwner(owner);
        assertEquals(owner, book.getOwner());
    }

}
