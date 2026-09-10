package com.rookies6.myspringboot4project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BookDetail {

    // ① BookDetail 자체 PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ② 도서 상세정보
    private String description;      // 설명
    private String language;      // 언어
    private Integer pageCount;     // 페이지 수
    private String publisher;      // 출판사
    private String coverImageUrl;      // 표지 이미지 URL
    private String edition;      // 에디션


    // ③ Book과 1:1 관계
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", unique = true)
    private Book book;
}