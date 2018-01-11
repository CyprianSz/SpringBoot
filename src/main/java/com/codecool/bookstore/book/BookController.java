package com.codecool.bookstore.book;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/books")
public class BookController {

    final Logger logger = Logger.getLogger(BookController.class);

    private BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping(path = "")
    public Book create(@RequestBody Book book) {
        this.service.save(book);
        return book;
    }

    @GetMapping(path = "")
    public Iterable<Book> showActive() {
        return this.service.findActive();
    }

    @GetMapping(path = "/all")
    public Iterable<Book> index() {
        return this.service.findAll();
    }

    @GetMapping(path = "/archived")
    public Iterable<Book> showArchived() {
        return this.service.findArchived();
    }

    @GetMapping(path = "/{id}")
    public Book show(@PathVariable Integer id) {
        return this.service.findOne(id);
    }

    @PutMapping(path = "/{id}")
    public Book update(@PathVariable("id") Integer id, @RequestBody Book book) throws IllegalAccessException {
        service.update(id, book);
        return book;
    }

    @DeleteMapping(path = "/{id}")
    public void archive(@PathVariable Integer id) {
        this.service.archive(id);
    }
}
