package com.codecool.bookstore.book;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
    Iterable<Book> findAllByArchivedIsFalse();
    Iterable<Book> findAllByArchivedIsTrue();
    Book findBookByIdAndArchivedIsFalse(Integer id);
    Book findBookByTitleAndGenreAndPublishedAndArchivedIsTrue(String title, String genre, Integer published);
}
