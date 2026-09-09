//package com.rookies6.myspringboot4project.service;
//
//import com.rookies6.myspringboot4project.controller.dto.BookDTO;
//import com.rookies6.myspringboot4project.entity.Book;
//import com.rookies6.myspringboot4project.repository.BookRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class BookService {
//
//    private final BookRepository bookRepository;
//
//    public BookService(BookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }
//
//
//    public BookDTO.BookResponse createBook(BookDTO.BookCreateRequest request) {
//
//        // ① CreateRequest DTO → Book Entity로 변환
//        Book book = new Book();
//
//        book.setTitle(request.getTitle());
//        book.setAuthor(request.getAuthor());
//        book.setIsbn(request.getIsbn());
//        book.setPrice(request.getPrice());
//        book.setPublishDate(request.getPublishDate());
//
//        // ② DB 저장
//        Book savedBook = bookRepository.save(book);
//
//        // ③ 저장된 Entity → BookResponse로 변환
//        BookDTO.BookResponse response = new BookDTO.BookResponse();
//
//        response.setId(savedBook.getId());
//        response.setTitle(savedBook.getTitle());
//        response.setAuthor(savedBook.getAuthor());
//        response.setIsbn(savedBook.getIsbn());
//        response.setPrice(savedBook.getPrice());
//        response.setPublishDate(savedBook.getPublishDate());
//
//        return response;
//    }
//
//    public List<BookDTO.BookResponse> getAllBooks() {
//
//        // ① DB에서 모든 Book 조회
//        List<Book> books = bookRepository.findAll();
//
//        // ② List<Book> → List<BookResponse>
//        return books.stream()
//                .map(book -> {
//                    BookDTO.BookResponse response = new BookDTO.BookResponse();
//
//                    response.setId(book.getId());
//                    response.setTitle(book.getTitle());
//                    response.setAuthor(book.getAuthor());
//                    response.setIsbn(book.getIsbn());
//                    response.setPrice(book.getPrice());
//                    response.setPublishDate(book.getPublishDate());
//
//                    return response;
//                })
//                .collect(Collectors.toList());
//    }
//
//    public BookDTO.BookResponse getBookById(Long id) {
//
//        // ① id로 Book 한 권 조회
//        Book book = bookRepository.findById(id)
//                .orElseThrow(() -> new );
//
//        // ② Book → BookResponse 변환
//        BookDTO.BookResponse response = new BookDTO.BookResponse();
//
//        response.setId(book.getId());
//        response.setTitle(book.getTitle());
//        response.setAuthor(book.getAuthor());
//        response.setIsbn(book.getIsbn());
//        response.setPrice(book.getPrice());
//        response.setPublishDate(book.getPublishDate());
//
//        return response;
//    }
//}
