package com.example.bookstore.book;

import com.example.bookstore.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ISBN;
    private String title;
    private String author;
    private String description;
    private String publisher;
    private String imageUrl;
    private int stock;

    @ManyToMany(mappedBy = "purchasedBooks")
    private List<User> purchasers;

    public Book(String title, String author, String description, String publisher, String imageUrl) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.publisher = publisher;
        this.imageUrl = imageUrl;
        this.stock = 10;
    }

    public Integer getISBN() {
        return ISBN;
    }

    public void setISBN(Integer ISBN) {
        this.ISBN = ISBN;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public List<User> getPurchasers() {
        return purchasers;
    }

    public void setPurchasers(List<User> purchasers) {
        this.purchasers = purchasers;
    }
}
