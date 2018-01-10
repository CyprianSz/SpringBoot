package com.codecool.bookstore.author;

import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepository repository;

    public AuthorServiceImpl(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<Author> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Author findOne(Integer id) {
        return this.repository.findOne(id);
    }

    @Override
    public void update(Integer id, Author author) {
        author.setId(id);
        repository.save(author);
    }

    @Override
    public void save(Author book) {
        this.repository.save(book);
    }

    @Override
    public void delete(Integer id) {
        this.repository.delete(id);
    }
}
