package com.gurkancintesun.book.book;

import lombok.Builder;

@Builder
public record BorrowedBookResponse(
        Integer id,
        String title,
        String authorName,
        String isbn,
        double rate,
        boolean returned,
        boolean returnedApproved
) {
}
