package project.logic;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.Book;
import project.models.Owner;
import project.persistence.BookRepository;
import project.persistence.OwnerRepository;

@Service
public class OwnerService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private OwnerRepository ownerRepository;

    // returns the owner if the given username and password are found in the database

    public void addNewBook(String username, Book book) {
        book.setOwner(getOwnerByUsername(username));
        bookRepository.save(book);
    }

    public void editBook(Long bookISBN, Book editedBook) {
        Book book = bookRepository.findById(bookISBN).orElseThrow(() -> new RuntimeException("Error finding book with ISBN: " + bookISBN));
        book.setTitle(editedBook.getTitle());
        book.setAuthor(editedBook.getAuthor());
        book.setDescription(editedBook.getDescription());
        book.setPublisher(editedBook.getPublisher());
        bookRepository.save(book);
    }

    public Owner getOwnerByUsername(String username) {
        return ownerRepository.findOwnerByUsername(username).orElseThrow(() -> new RuntimeException("error finding owner"));
    }

    public Book getBookById(Long ISBN) {
        return bookRepository.findById(ISBN).orElseThrow(() -> new RuntimeException("error finding book"));
    }
}
