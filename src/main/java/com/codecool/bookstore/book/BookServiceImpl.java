package com.codecool.bookstore.book;

import com.codecool.bookstore.author.Author;
import com.codecool.bookstore.author.AuthorRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository repository;
    private AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository repository, AuthorRepository authorRepository) {
        this.repository = repository;
        this.authorRepository = authorRepository;
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
        Author givenAuthor = book.getAuthor();
        Author author = authorRepository.findAuthorByFirstName( givenAuthor.getFirstName());

        if (author != null) {
            book.setAuthor( author );
        }
        this.repository.save(book);
    }

//    @Override
//    public void update(Integer id, Integer authorId, Book book) {
//        book.setId(id);
//        Author author = authorRepository.findOne(authorId);
//        book.setAuthor( author );
//        repository.save(book);
//    }

    @Override
    public void update(Integer id, Book book) throws IllegalAccessException {
        book.setId(id);
        Book bookValidatedForAuthorExistence = validateIfAuthorExists( book );
        Book bookValidatedForAllFieldsExistence = checkIfAnyFieldIsNull( bookValidatedForAuthorExistence );

        repository.save(bookValidatedForAllFieldsExistence);
    }

    @Override
    public void delete(Integer id) {
        this.repository.delete(id);
    }

    private Book validateIfAuthorExists(Book book) {
        Author givenAuthor = book.getAuthor();
        Author author = authorRepository.findAuthorByFirstName(givenAuthor.getFirstName());

        if (author != null) {
            book.setAuthor( author );
            return book;
        } else {
            throw new IllegalArgumentException( );
        }
    }

    private Book checkIfAnyFieldIsNull(Book book) throws IllegalAccessException {
        Field[] fields = book.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible( true );
            if (field.get(book) == null ) {
                throw new IllegalArgumentException( );
            }
        }
        return book;
    }
}
