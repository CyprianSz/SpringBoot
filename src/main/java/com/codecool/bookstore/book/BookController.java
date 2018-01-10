package com.codecool.bookstore.book;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/books")
public class BookController {

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
    public Iterable<Book> index() {
        return this.service.findAll();
    }

    @GetMapping(path = "/{id}")
    public Book show(@PathVariable Integer id) {
        return this.service.findOne(id);
    }

//    @PutMapping(path = "/{id}/author/{authorId}")
//    public Book update(@PathVariable("id") Integer id,
//                       @PathVariable("authorId") Integer authorId,
//                       @RequestBody Book book){
//        service.update(id, authorId, book);
//        return book;
//    }

    @PutMapping(path = "/{id}")
    public Book update(@PathVariable("id") Integer id, @RequestBody Book book) throws IllegalAccessException {
        service.update(id, book);
        return book;
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Integer id) {
        this.service.delete(id);
    }
}
