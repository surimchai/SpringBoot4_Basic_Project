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
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public BookDTO.BookResponse createBook(BookDTO.BookCreateRequest request) {

        // ① CreateRequest DTO → Book Entity로 변환
        Book book = new Book();

        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setIsbn(request.getIsbn());
        book.setPrice(request.getPrice());
        book.setPublishDate(request.getPublishDate());

        // ② DB 저장
        Book savedBook = bookRepository.save(book);

        // ③ 저장된 Entity → BookResponse로 변환
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

        // ① DB에서 모든 Book 조회
        List<Book> books = bookRepository.findAll();

        // ② List<Book> → List<BookResponse>
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

        // ① id로 Book 한 권 조회
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.RESOURCE_NOT_FOUND, "Book", "id", id
                ));

        // ② Book → BookResponse 변환
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

        // ① ISBN으로 조회
        Book book = bookRepository.findByIsbn(isbn);

        // ② 조회 결과가 없으면 예외
        if (book == null) {
            throw new BusinessException(
                    ErrorCode.RESOURCE_NOT_FOUND, "Book", "isbn", isbn);
        }

        // ③ Book → BookResponse 변환
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

        // ① 수정할 Book 찾기
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.RESOURCE_NOT_FOUND, "Book", "id", id));

        // ② 요청으로 들어온 값만 수정
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

        // ③ 저장
        Book updatedBook = bookRepository.save(book);

        // TODO ④ updatedBook → BookResponse 변환
        // ④ updatedBook → BookResponse 변환
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

        // ① 삭제할 Book이 존재하는지 확인
        if (!bookRepository.existsById(id)) {
            throw new BusinessException(
                    ErrorCode.RESOURCE_NOT_FOUND,
                    "Book", "id", id
            );
        }

        // ② 삭제
        bookRepository.deleteById(id);
    }
}