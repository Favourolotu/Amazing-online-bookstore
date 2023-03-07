package project;
import java.util.*;
import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    private Long isbn;
    private String name;
    private String author;
    private String description;
    private String publisher;

    public Book(){

    }

    public Long getIsbn() {
        return isbn;
    }
    public void setIsbn(Long isbn){
        this.isbn = isbn;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return String.format(
                "Book[isbn=%d, name='%s', author='%s']",
                isbn, name, author);
    }
}
