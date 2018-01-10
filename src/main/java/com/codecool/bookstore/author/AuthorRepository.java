package com.codecool.bookstore.author;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer> {
    Author findAuthorByFirstName(String firstName);
}
