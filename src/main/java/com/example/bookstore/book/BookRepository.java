package com.example.bookstore.book;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends CrudRepository<Book, Integer> {

    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(concat('%', :query, '%')) OR LOWER(b.author) LIKE LOWER(concat('%', :query, '%')) OR LOWER(b.publisher) LIKE LOWER(concat('%', :query, '%'))")
    Iterable<Book> search(@Param("query") String query);


}
