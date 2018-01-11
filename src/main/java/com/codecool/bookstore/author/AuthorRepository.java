package com.codecool.bookstore.author;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer> {
    Iterable<Author> findAllByArchivedIsFalse();
    Iterable<Author> findAllByArchivedIsTrue();
    Author findAuthorByFirstNameAndLastName(String firstName, String lastName);
    Author findAuthorByIdAndArchivedIsFalse(Integer id);
    Author findAuthorByFirstNameAndLastNameAndArchivedIsTrue(String firstName, String lastName);
}
