package com.codecool.bookstore.author;

public interface AuthorService {

    Iterable<Author> findAll();

    Author findOne(Integer id);

    void save(Author book);

    void update(Integer id, Author author);

    void delete(Integer id);
}
