package com.codecool.bookstore.book;

public interface BookService {

    Iterable<Book> findAll();

    Book findOne(Integer id);

    void save(Book book);

    void update(Integer id, Book book) throws IllegalAccessException;

    void delete(Integer id);
}
