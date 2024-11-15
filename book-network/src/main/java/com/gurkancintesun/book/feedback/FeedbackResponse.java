package com.gurkancintesun.book.feedback;

import lombok.Builder;

@Builder
public record FeedbackResponse(
        Double note,
        String comment,
        boolean ownFeedback
) {
}
