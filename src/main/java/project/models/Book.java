package project.models;

import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ISBN;
    private String title;
    private String author;
    private String description;
    private String publisher;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Owner owner;

    public Book(){}

    public Book(String title, String author, String description, String publisher) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.publisher = publisher;
    }

    public Long getISBN() {
        return ISBN;
    }
    public void setISBN(Long isbn){
        this.ISBN = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
