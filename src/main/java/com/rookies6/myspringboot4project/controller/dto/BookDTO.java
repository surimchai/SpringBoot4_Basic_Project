package com.rookies6.myspringboot4project.controller.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class BookDTO {

    @Getter
    @Setter
    public static class Request {

        @NotBlank
        private String title;

        @NotBlank
        private String author;

        @NotBlank
        private String isbn;

        @PositiveOrZero
        private Integer price;

        @PastOrPresent
        private LocalDate publishDate;

        @Valid
        private BookDetailDTO detailRequest;
    }

    @Getter
    @Setter
    public static class BookDetailDTO {
        private String description;
        private String language;

        @PositiveOrZero
        private Integer pageCount;

        private String publisher;
        private String coverImageUrl;
        private String edition;
    }

    @Getter
    @Setter
    public static class Response {
        private Long id;
        private String title;
        private String author;
        private String isbn;
        private Integer price;
        private LocalDate publishDate;

        private BookDetailResponse detail;
    }

    @Getter
    @Setter
    public static class BookDetailResponse {
        private Long id;
        private String description;
        private String language;
        private Integer pageCount;
        private String publisher;
        private String coverImageUrl;
        private String edition;
    }
}