package com.codecool.bookstore.book;

import com.codecool.bookstore.logger.LogService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository repository;
    private BookServiceHelper bookServiceHelper;
    private Logger logger;

    public BookServiceImpl(BookRepository repository,
                           BookServiceHelper bookServiceHelper,
                           LogService logService) {
        this.repository = repository;
        this.bookServiceHelper = bookServiceHelper;
        this.logger = logService.getLogger();
    }

    @Override
    public Iterable<Book> findAll() {
        return this.repository.findAll();
    }

    public Iterable<Book> findActive() {
        logger.info("books list returned");
        return this.repository.findAllByArchivedIsFalse();
    }

    public Iterable<Book> findArchived() {
        return this.repository.findAllByArchivedIsTrue();
    }

    @Override
    public Book findOne(Integer id) {
        Book book = this.repository.findBookByIdAndArchivedIsFalse(id);

        if (book != null) {
            logger.info("book returned");
            return book;
        } else {
            throw new IllegalArgumentException(  );
        }
    }

    @Override
    public void save(Book book) {
        Book updatedBook = bookServiceHelper.setAuthorIfAlreadyExists(book);
        this.repository.save(updatedBook);
        logger.info("book created");
    }

    @Override
    public void update(Integer id, Book book) throws IllegalAccessException {
        book.setId(id);
        bookServiceHelper.checkIfBookNotArchived(book);

        Book bookValidatedForAuthorExistence =
                bookServiceHelper.validateIfAuthorExists( book );
        Book bookValidatedForAllFieldsExistence =
                bookServiceHelper.checkIfAnyFieldIsNull( bookValidatedForAuthorExistence );

        repository.save(bookValidatedForAllFieldsExistence);
        logger.info("book updated");
    }

    @Override
    public void delete(Integer id) {
        this.repository.delete(id);
    }

    public void archive(Integer id) {
        Book book = repository.findOne( id );
        Book foundedBook = bookServiceHelper.searchForSameAlreadyArchived(book);

        if ((foundedBook != null) && (!Objects.equals( foundedBook.getId(), id ))) {
            repository.delete( id );
        } else {
            book.setArchived( true );
            repository.save(book);
        }
        logger.info("book deleted");
    }
}
