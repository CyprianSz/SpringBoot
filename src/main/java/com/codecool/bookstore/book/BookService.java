package com.codecool.bookstore.book;

public interface BookService {

    Iterable<Book> findAll();

    Iterable<Book> findActive();

    Iterable<Book> findArchived();

    Book findOne(Integer id);

    void save(Book book);

    void update(Integer id, Book book) throws IllegalAccessException;

    void delete(Integer id);

    void archive(Integer id);
}
