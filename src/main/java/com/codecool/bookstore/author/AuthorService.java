package com.codecool.bookstore.author;

public interface AuthorService {

    Iterable<Author> findAll();

    Iterable<Author> findActive();

    Iterable<Author> findArchived();

    Author findOne(Integer id);

    void save(Author book);

    void update(Integer id, Author author) throws IllegalAccessException;

    void delete(Integer id);

    void archive(Integer id);
}
