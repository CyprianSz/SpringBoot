package com.codecool.bookstore.author;

import com.codecool.bookstore.logger.LogService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepository repository;
    private Logger logger;

    public AuthorServiceImpl(AuthorRepository repository, LogService logService) {
        this.repository = repository;
        this.logger = logService.getLogger();
    }

    @Override
    public Iterable<Author> findAll() {
        return this.repository.findAll();
    }

    public Iterable<Author> findActive() {
        logger.info("authors list returned");
        return this.repository.findAllByArchivedIsFalse();
    }

    public Iterable<Author> findArchived() {
        return this.repository.findAllByArchivedIsTrue();
    }


    @Override
    public Author findOne(Integer id) {
        Author author = this.repository.findAuthorByIdAndArchivedIsFalse(id);

        if (author != null) {
            logger.info("author returned");
            return author;
        } else {
            throw new IllegalArgumentException(  );
        }
    }

    @Override
    public void update(Integer id, Author author) throws IllegalAccessException {
        author.setId(id);

        if (author.isArchived()) {
            throw new IllegalAccessException();
        } else {
            repository.save(author);
            logger.info("author updated");
        }
    }

    @Override
    public void save(Author book) {
        this.repository.save(book);
        logger.info("author created");
    }

    @Override
    public void delete(Integer id) {
        this.repository.delete(id);
    }

    public void archive(Integer id) {
        Author author = repository.findOne( id );
        String firstName = author.getFirstName();
        String lastName = author.getLastName();

        Author foundedAuthor = repository.findAuthorByFirstNameAndLastNameAndArchivedIsTrue( firstName, lastName );

        if ((foundedAuthor != null) && (!Objects.equals( foundedAuthor.getId(), id ))) {
            repository.delete( id );
        } else {
            author.setArchived( true );
            repository.save(author);
        }
        logger.info("author deleted");
    }
}
