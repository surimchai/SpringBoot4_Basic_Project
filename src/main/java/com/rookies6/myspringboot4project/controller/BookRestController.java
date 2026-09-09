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

    // 도서 등록
    @PostMapping
    public BookDTO.BookResponse createBook(
            @Valid @RequestBody BookDTO.BookCreateRequest request) {
        return bookService.createBook(request);
    }

    // 전체 도서 조회
    @GetMapping
    public List<BookDTO.BookResponse> getAllBooks() {
        return bookService.getAllBooks();
    }

    // ID로 도서 조회
    @GetMapping("/{id}")
    public BookDTO.BookResponse getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    // ISBN으로 도서 조회
    @GetMapping("/isbn/{isbn}")
    public BookDTO.BookResponse getBookByIsbn(@PathVariable String isbn) {
        return bookService.getBookByIsbn(isbn);
    }

    // 도서 수정
    @PutMapping("/{id}")
    public BookDTO.BookResponse updateBook(
            @PathVariable Long id,
            @Valid @RequestBody BookDTO.BookUpdateRequest request) {
        return bookService.updateBook(id, request);
    }

    // 도서 삭제
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}