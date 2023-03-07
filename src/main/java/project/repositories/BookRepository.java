package project.repositories;

import org.springframework.data.repository.CrudRepository;
import project.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
}
