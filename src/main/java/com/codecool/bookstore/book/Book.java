package com.codecool.bookstore.book;

import com.codecool.bookstore.author.Author;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames={"title", "genre", "published"}))
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String title;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Author author;

    @NotNull
    private String genre;

    @NotNull
    private Integer published;

    private boolean archived;

    public Book() { }

    public Book(String title, Author author, String genre, Integer published) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.published = published;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getPublished() {
        return published;
    }

    public void setPublished(Integer published) {
        this.published = published;
    }
}
