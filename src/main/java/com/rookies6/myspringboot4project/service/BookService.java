package com.rookies6.myspringboot4project.service;

import com.rookies6.myspringboot4project.controller.dto.BookDTO;
import com.rookies6.myspringboot4project.entity.Book;
import com.rookies6.myspringboot4project.entity.BookDetail;
import com.rookies6.myspringboot4project.exception.BusinessException;
import com.rookies6.myspringboot4project.exception.ErrorCode;
import com.rookies6.myspringboot4project.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDTO.Response> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public BookDTO.Response getBookById(Long id) {
        Book book = bookRepository.findByIdWithBookDetail(id)
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.RESOURCE_NOT_FOUND, "Book", "id", id));

        return toResponse(book);
    }

    public BookDTO.Response getBookByIsbn(String isbn) {
        Book book = bookRepository.findByIsbnWithBookDetail(isbn)
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.RESOURCE_NOT_FOUND, "Book", "isbn", isbn));

        return toResponse(book);
    }

    public List<BookDTO.Response> getBooksByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<BookDTO.Response> getBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public BookDTO.Response createBook(BookDTO.Request request) {

        if (bookRepository.existsByIsbn(request.getIsbn())) {
            throw new BusinessException(
                    ErrorCode.ISBN_DUPLICATE, request.getIsbn());
        }

        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setIsbn(request.getIsbn());
        book.setPrice(request.getPrice());
        book.setPublishDate(request.getPublishDate());

        if (request.getDetailRequest() != null) {
            BookDetail detail = new BookDetail();

            detail.setDescription(request.getDetailRequest().getDescription());
            detail.setLanguage(request.getDetailRequest().getLanguage());
            detail.setPageCount(request.getDetailRequest().getPageCount());
            detail.setPublisher(request.getDetailRequest().getPublisher());
            detail.setCoverImageUrl(request.getDetailRequest().getCoverImageUrl());
            detail.setEdition(request.getDetailRequest().getEdition());

            // 양방향 관계 연결
            detail.setBook(book);
            book.setBookDetail(detail);
        }

        return toResponse(bookRepository.save(book));
    }

    @Transactional
    public BookDTO.Response updateBook(Long id, BookDTO.Request request) {

        Book book = bookRepository.findByIdWithBookDetail(id)
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.RESOURCE_NOT_FOUND, "Book", "id", id));

        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setPrice(request.getPrice());
        book.setPublishDate(request.getPublishDate());

        if (request.getDetailRequest() != null) {
            BookDetail detail = book.getBookDetail();

            if (detail == null) {
                detail = new BookDetail();
                detail.setBook(book);
                book.setBookDetail(detail);
            }

            detail.setDescription(request.getDetailRequest().getDescription());
            detail.setLanguage(request.getDetailRequest().getLanguage());
            detail.setPageCount(request.getDetailRequest().getPageCount());
            detail.setPublisher(request.getDetailRequest().getPublisher());
            detail.setCoverImageUrl(request.getDetailRequest().getCoverImageUrl());
            detail.setEdition(request.getDetailRequest().getEdition());
        }

        return toResponse(book);
    }

    @Transactional
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.RESOURCE_NOT_FOUND, "Book", "id", id));

        bookRepository.delete(book);
    }

    private BookDTO.Response toResponse(Book book) {

        BookDTO.Response response = new BookDTO.Response();

        response.setId(book.getId());
        response.setTitle(book.getTitle());
        response.setAuthor(book.getAuthor());
        response.setIsbn(book.getIsbn());
        response.setPrice(book.getPrice());
        response.setPublishDate(book.getPublishDate());

        if (book.getBookDetail() != null) {
            BookDetail detail = book.getBookDetail();

            BookDTO.BookDetailResponse detailResponse =
                    new BookDTO.BookDetailResponse();

            detailResponse.setId(detail.getId());
            detailResponse.setDescription(detail.getDescription());
            detailResponse.setLanguage(detail.getLanguage());
            detailResponse.setPageCount(detail.getPageCount());
            detailResponse.setPublisher(detail.getPublisher());
            detailResponse.setCoverImageUrl(detail.getCoverImageUrl());
            detailResponse.setEdition(detail.getEdition());

            response.setDetail(detailResponse);
        }

        return response;
    }
}