package com.gurkancintesun.book.auth;

import lombok.Builder;

@Builder
public record AuthenticateResponse(
        String token
) {
}
