package project;
import java.util.*;
import jakarta.persistence.*;

@Entity
public class Inventory {
    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> books;

    public Inventory(){
        this.books = new LinkedList<>();
    }

    public List<Book> getBooks() {
        return books;
    }
}
