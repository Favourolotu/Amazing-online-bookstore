package project.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private List<Book> bookList;

    public ShoppingCart(){
        bookList = new ArrayList<>();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public List<Book> getBookList(){
        return this.bookList;
    }

    public void addBook (Book book){
        this.bookList.add(book);
    }

    public void setBookList(Book book){
        this.bookList.add(book);
    }

    public void removeBook (Book book){
        this.bookList.remove(book);
    }

    public void clear(){
        this.bookList = new ArrayList<Book>();
    }


}
