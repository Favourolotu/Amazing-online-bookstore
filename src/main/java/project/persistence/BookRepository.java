package project.persistence;

import project.models.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByTitle(String bookName);
    Book findByISBN(Long ISBN);
}

