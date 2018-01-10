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
    public Iterable<Author> index() {
        return this.service.findAll();
    }

    @GetMapping(path = "/{id}")
    public Author show(@PathVariable Integer id) {
        return this.service.findOne( id );
    }

    @PutMapping(path = "/{id}")
    public void update(@PathVariable Integer id, @RequestBody Author author) {
        service.update(id, author);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Integer id) {
        this.service.delete(id);
    }
}
