package com.codecool.bookstore.book;

import com.codecool.bookstore.author.Author;
import com.codecool.bookstore.author.AuthorRepository;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class BookServiceHelper {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public BookServiceHelper(AuthorRepository authorRepository,
                             BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public AuthorRepository getAuthorRepository() {
        return authorRepository;
    }

    Book setAuthorIfAlreadyExists(Book book) {
        Author author = searchForGivenAuthor( book );

        if (author != null) {
            book.setAuthor(author);
        }
        return book;
    }

    Book validateIfAuthorExists(Book book) {
        Author author = searchForGivenAuthor( book );

        if (author != null) {
            book.setAuthor( author );
            return book;
        } else {
            throw new IllegalArgumentException( );
        }
    }

    Author searchForGivenAuthor(Book book) {
        Author givenAuthor = book.getAuthor();
        String firstName = givenAuthor.getFirstName();
        String lastName = givenAuthor.getLastName();
        return authorRepository.findAuthorByFirstNameAndLastName(firstName, lastName);
    }

    Book checkIfAnyFieldIsNull(Book book) throws IllegalAccessException {
        Field[] fields = book.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible( true );
            if (field.get(book) == null ) {
                throw new IllegalArgumentException( );
            }
        }
        return book;
    }

    void checkIfBookNotArchived(Book book) throws IllegalAccessException {
        if (book.isArchived()) {
            throw new IllegalAccessException();
        }
    }

    Book searchForSameAlreadyArchived(Book book) {
        String title = book.getTitle();
        String genre = book.getGenre();
        Integer published = book.getPublished();

        return bookRepository.findBookByTitleAndGenreAndPublishedAndArchivedIsTrue( title, genre, published );
    }
}
