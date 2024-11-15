package com.gurkancintesun.book.book;

import jakarta.validation.constraints.NotBlank;

public record BookRequest(
        Integer id,
        @NotBlank(message = "100")
        String title,
        @NotBlank(message = "101")
        String authorName,
        @NotBlank(message = "102")
        String synopsis,
        @NotBlank(message = "103")
        String isbn,
        boolean shareable
) {
}
