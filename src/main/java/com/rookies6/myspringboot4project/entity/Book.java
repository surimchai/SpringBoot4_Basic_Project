package com.rookies6.myspringboot4project.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Book {

    // TODO: PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;

    @Column(unique = true)
    private String isbn;

    private LocalDate publishDate;
    private Integer price;
}