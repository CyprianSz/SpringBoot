package com.codecool.bookstore.author;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/authors")
public class AuthorController {

    private AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @PostMapping(path = "")
    public Author create(@RequestBody Author author) {
        this.service.save(author);
        return author;
    }

    @GetMapping(path = "")
    public Iterable<Author> showActive() {
        return this.service.findActive();
    }

    @GetMapping(path = "/all")
    public Iterable<Author> index() {
        return this.service.findAll();
    }

    @GetMapping(path = "/archived")
    public Iterable<Author> showArchived() {
        return this.service.findArchived();
    }

    @GetMapping(path = "/{id}")
    public Author show(@PathVariable Integer id) {
        return this.service.findOne( id );
    }

    @PutMapping(path = "/{id}")
    public Author update(@PathVariable Integer id, @RequestBody Author author) throws IllegalAccessException {
        service.update(id, author);
        return author;
    }

    @DeleteMapping(path = "/{id}")
    public void archive(@PathVariable Integer id) {
        this.service.archive(id);
    }
}
