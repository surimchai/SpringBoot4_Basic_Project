package com.rookies6.myspringboot4project.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class BookDTO {
    @Getter
    @Setter
    public static class BookCreateRequest {

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
    }

    @Getter
    @Setter
    public static class BookUpdateRequest {

        private String title;
        private String author;

        @PositiveOrZero
        private Integer price;

        @PastOrPresent
        private LocalDate publishDate;
    }

    @Getter
    @Setter
    public static class BookResponse {

        private Long id;
        private String title;
        private String author;
        private String isbn;
        private Integer price;
        private LocalDate publishDate;
    }
}