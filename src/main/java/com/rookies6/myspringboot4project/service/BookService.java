package com.rookies6.myspringboot4project.service;

import com.rookies6.myspringboot4project.controller.dto.BookDTO;
import com.rookies6.myspringboot4project.entity.Book;
import com.rookies6.myspringboot4project.repository.BookRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.rookies6.myspringboot4project.exception.BusinessException;
import com.rookies6.myspringboot4project.exception.ErrorCode;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public BookDTO.BookResponse createBook(BookDTO.BookCreateRequest request) {

        Book book = new Book();

        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setIsbn(request.getIsbn());
        book.setPrice(request.getPrice());
        book.setPublishDate(request.getPublishDate());

        Book savedBook = bookRepository.save(book);

        BookDTO.BookResponse response = new BookDTO.BookResponse();

        response.setId(savedBook.getId());
        response.setTitle(savedBook.getTitle());
        response.setAuthor(savedBook.getAuthor());
        response.setIsbn(savedBook.getIsbn());
        response.setPrice(savedBook.getPrice());
        response.setPublishDate(savedBook.getPublishDate());

        return response;
    }

    public List<BookDTO.BookResponse> getAllBooks() {

        List<Book> books = bookRepository.findAll();

        return books.stream()
                .map(book -> {
                    BookDTO.BookResponse response = new BookDTO.BookResponse();

                    response.setId(book.getId());
                    response.setTitle(book.getTitle());
                    response.setAuthor(book.getAuthor());
                    response.setIsbn(book.getIsbn());
                    response.setPrice(book.getPrice());
                    response.setPublishDate(book.getPublishDate());

                    return response;
                })
                .collect(Collectors.toList());
    }

    public BookDTO.BookResponse getBookById(Long id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.RESOURCE_NOT_FOUND, "Book", "id", id
                ));

        BookDTO.BookResponse response = new BookDTO.BookResponse();

        response.setId(book.getId());
        response.setTitle(book.getTitle());
        response.setAuthor(book.getAuthor());
        response.setIsbn(book.getIsbn());
        response.setPrice(book.getPrice());
        response.setPublishDate(book.getPublishDate());

        return response;
    }

    public BookDTO.BookResponse getBookByIsbn(String isbn) {

        Book book = bookRepository.findByIsbn(isbn);

        if (book == null) {
            throw new BusinessException(
                    ErrorCode.RESOURCE_NOT_FOUND, "Book", "isbn", isbn);
        }

        BookDTO.BookResponse response = new BookDTO.BookResponse();

        response.setId(book.getId());
        response.setTitle(book.getTitle());
        response.setAuthor(book.getAuthor());
        response.setIsbn(book.getIsbn());
        response.setPrice(book.getPrice());
        response.setPublishDate(book.getPublishDate());

        return response;
    }

    @Transactional
    public BookDTO.BookResponse updateBook(Long id, BookDTO.BookUpdateRequest request) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.RESOURCE_NOT_FOUND, "Book", "id", id));

        if (request.getTitle() != null) {
            book.setTitle(request.getTitle());
        }

        if (request.getAuthor() != null) {
            book.setAuthor(request.getAuthor());
        }

        if (request.getPrice() != null) {
            book.setPrice(request.getPrice());
        }

        if (request.getPublishDate() != null) {
            book.setPublishDate(request.getPublishDate());
        }

        Book updatedBook = bookRepository.save(book);

        BookDTO.BookResponse response = new BookDTO.BookResponse();

        response.setId(updatedBook.getId());
        response.setTitle(updatedBook.getTitle());
        response.setAuthor(updatedBook.getAuthor());
        response.setIsbn(updatedBook.getIsbn());
        response.setPrice(updatedBook.getPrice());
        response.setPublishDate(updatedBook.getPublishDate());

        return response;
    }

    @Transactional
    public void deleteBook(Long id) {

        if (!bookRepository.existsById(id)) {
            throw new BusinessException(
                    ErrorCode.RESOURCE_NOT_FOUND,
                    "Book", "id", id
            );
        }

        bookRepository.deleteById(id);
    }
}