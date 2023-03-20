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
public class InventoryRepositoryTest {
    @Autowired
    private InventoryRepository inventoryRepo;
    private Book book1;
    private Book book2;
    private Inventory inventory;


    @Test
    public void addToInventory() {
        long initialCount = inventoryRepo.count();
        book1 = new Book("percy jackson", "e.b. things", "greek gods", "rick");
        book2 = new Book("game of thrones", "j.r. martin", "dragons and shit", "dean");
        inventory = new Inventory();
        inventory.addBook(book1);
        inventory.addBook(book2);
        inventoryRepo.save(inventory);
        assertEquals( initialCount + 1, inventoryRepo.count());
    }
}
