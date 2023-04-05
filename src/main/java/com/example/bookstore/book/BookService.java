package com.example.bookstore.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Iterable<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    public void addBook(Book book) {
        this.bookRepository.save(book);
    }

    public Book getBookByISBN(Integer ISBN) {
        return this.bookRepository.findById(ISBN)
                .orElseThrow(() -> new RuntimeException("Error finding book with ISBN: " + ISBN));
    }

    public Iterable<Book> getBooksByQuery(String query) {
        return this.bookRepository.search(query);

    }

    public Iterable<Book> sortBooksByQuery(String query) {
        switch (query) {
            case "title" -> {
                return this.bookRepository.findAllByOrderByTitleAsc();
            }
            case "author" -> {
                return this.bookRepository.findAllByOrderByAuthorAsc();
            }
            case "publisher" -> {
                return this.bookRepository.findAllByOrderByPublisherAsc();
            }
            case "stock" -> {
                return this.bookRepository.findAllByOrderByStockAsc();
            }
        }
        // somehow invalid sort param passed
        return null;
    }

    public void addNewBook(Book book) {
        bookRepository.save(book);
    }

    public void editBook(Integer bookISBN, Book editedBook) {
        Book book = bookRepository.findById(bookISBN).orElseThrow(() -> new RuntimeException("Error finding book with ISBN: " + bookISBN));
        book.setTitle(editedBook.getTitle());
        book.setAuthor(editedBook.getAuthor());
        book.setDescription(editedBook.getDescription());
        book.setPublisher(editedBook.getPublisher());
        book.setImageUrl(editedBook.getImageUrl());
        book.setStock(editedBook.getStock());
        bookRepository.save(book);

    }
}
