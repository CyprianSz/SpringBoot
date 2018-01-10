package com.codecool.bookstore.book;

import com.codecool.bookstore.author.Author;
import com.codecool.bookstore.author.AuthorRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository repository;
    private AuthorRepository authorRepository;
    private BookServiceHelper bookServiceHelper;

    public BookServiceImpl(BookRepository repository,
                           AuthorRepository authorRepository,
                           BookServiceHelper bookServiceHelper) {
        this.repository = repository;
        this.authorRepository = authorRepository;
        this.bookServiceHelper = bookServiceHelper;
    }

    @Override
    public Iterable<Book> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Book findOne(Integer id) {
        return this.repository.findOne(id);
    }

    @Override
    public void save(Book book) {
        Book updatedBook = bookServiceHelper.setAuthorIfAlreadyExists(book);
        this.repository.save(updatedBook);
    }

    @Override
    public void update(Integer id, Book book) throws IllegalAccessException {
        book.setId(id);
        Book bookValidatedForAuthorExistence =
                bookServiceHelper.validateIfAuthorExists( book );
        Book bookValidatedForAllFieldsExistence =
                bookServiceHelper.checkIfAnyFieldIsNull( bookValidatedForAuthorExistence );

        repository.save(bookValidatedForAllFieldsExistence);
    }

    @Override
    public void delete(Integer id) {
        this.repository.delete(id);
    }
}
