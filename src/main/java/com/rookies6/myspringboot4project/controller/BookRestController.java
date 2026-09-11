package com.rookies6.myspringboot4project.controller;

import com.rookies6.myspringboot4project.controller.dto.BookDTO;
import com.rookies6.myspringboot4project.service.BookService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public BookDTO.Response createBook(
            @Valid @RequestBody BookDTO.Request request) {
        return bookService.createBook(request);
    }

    @GetMapping
    public List<BookDTO.Response> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public BookDTO.Response getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/isbn/{isbn}")
    public BookDTO.Response getBookByIsbn(@PathVariable String isbn) {
        return bookService.getBookByIsbn(isbn);
    }

    @GetMapping("/author")
    public List<BookDTO.Response> getBooksByAuthor(
            @RequestParam String author) {
        return bookService.getBooksByAuthor(author);
    }

    @GetMapping("/title")
    public List<BookDTO.Response> getBooksByTitle(
            @RequestParam String title) {
        return bookService.getBooksByTitle(title);
    }

    @PutMapping("/{id}")
    public BookDTO.Response updateBook(
            @PathVariable Long id,
            @Valid @RequestBody BookDTO.Request request) {
        return bookService.updateBook(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}