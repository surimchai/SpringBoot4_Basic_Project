package com.rookies6.myspringboot4project.repository;

import com.rookies6.myspringboot4project.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
    }

    @Test
    void testCreateBook() {
        Book book = new Book();
        book.setTitle("스프링 부트 입문");
        book.setAuthor("홍길동");
        book.setIsbn("9788956746425");
        book.setPrice(30000);
        book.setPublishDate(LocalDate.of(2025, 5, 7));

        Book savedBook = bookRepository.save(book);

        assertNotNull(savedBook.getId());
        assertEquals("스프링 부트 입문", savedBook.getTitle());
    }

    @Test
    void testFindByIsbn() {
        Book book = new Book();
        book.setTitle("JPA 프로그래밍");
        book.setAuthor("박둘리");
        book.setIsbn("9788956746432");
        book.setPrice(35000);
        book.setPublishDate(LocalDate.of(2025, 4, 30));

        bookRepository.save(book);

        Book foundBook = bookRepository.findByIsbn("9788956746432");

        assertNotNull(foundBook);
        assertEquals("JPA 프로그래밍", foundBook.getTitle());
        assertEquals("박둘리", foundBook.getAuthor());
    }

    @Test
    void testFindByAuthor() {
        Book book1 = new Book();
        book1.setTitle("스프링 부트 입문");
        book1.setAuthor("홍길동");
        book1.setIsbn("9788956746425");
        book1.setPrice(30000);
        book1.setPublishDate(LocalDate.of(2025, 5, 7));

        Book book2 = new Book();
        book2.setTitle("스프링 심화");
        book2.setAuthor("홍길동");
        book2.setIsbn("9788956746449");
        book2.setPrice(40000);
        book2.setPublishDate(LocalDate.of(2025, 6, 1));

        bookRepository.save(book1);
        bookRepository.save(book2);

        List<Book> books = bookRepository.findByAuthor("홍길동");

        assertEquals(2, books.size());
        assertEquals("홍길동", books.get(0).getAuthor());
    }

    @Test
    void testUpdateBook() {
        Book book = new Book();
        book.setTitle("스프링 부트 입문");
        book.setAuthor("홍길동");
        book.setIsbn("9788956746425");
        book.setPrice(30000);
        book.setPublishDate(LocalDate.of(2025, 5, 7));

        Book savedBook = bookRepository.save(book);

        savedBook.setPrice(35000);

        Book updatedBook = bookRepository.save(savedBook);

        assertEquals(35000, updatedBook.getPrice());
    }

    @Test
    void testDeleteBook() {
        Book book = new Book();
        book.setTitle("스프링 부트 입문");
        book.setAuthor("홍길동");
        book.setIsbn("9788956746425");
        book.setPrice(30000);
        book.setPublishDate(LocalDate.of(2025, 5, 7));

        Book savedBook = bookRepository.save(book);
        Long id = savedBook.getId();

        bookRepository.deleteById(id);

        assertFalse(bookRepository.findById(id).isPresent());
    }
}