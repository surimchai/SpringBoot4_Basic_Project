package com.rookies6.myspringboot4project.repository;

import com.rookies6.myspringboot4project.entity.Book;
import com.rookies6.myspringboot4project.entity.BookDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class BookDetailRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookDetailRepository bookDetailRepository;

    @Test
    void testBookDetail() {

        Book book = new Book();
        book.setTitle("스프링 부트");
        book.setAuthor("홍길동");
        book.setIsbn("978-1234567890");
        book.setPrice(30000);
        book.setPublishDate(LocalDate.of(2026, 9, 11));

        Book savedBook = bookRepository.save(book);

        BookDetail detail = new BookDetail();
        detail.setDescription("스프링 부트 실습 도서");
        detail.setLanguage("Korean");
        detail.setPageCount(500);
        detail.setPublisher("루키즈출판");
        detail.setCoverImageUrl("cover.jpg");
        detail.setEdition("1st");

        detail.setBook(savedBook);

        BookDetail savedDetail = bookDetailRepository.save(detail);

        System.out.println(savedDetail.getId());
        System.out.println(savedDetail.getBook().getTitle());
    }
}