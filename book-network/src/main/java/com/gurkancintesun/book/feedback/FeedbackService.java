package com.gurkancintesun.book.feedback;

import com.gurkancintesun.book.book.Book;
import com.gurkancintesun.book.book.BookRepository;
import com.gurkancintesun.book.common.PageResponse;
import com.gurkancintesun.book.exception.OperationNotPermittedException;
import com.gurkancintesun.book.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final BookRepository bookRepository;
    private final FeedbackMapper feedbackMapper;

    public Integer saveFeedback(FeedbackRequest request, Authentication connectedUser) {
        Book book = bookRepository.findById(request.bookId())
                .orElseThrow(() -> new EntityNotFoundException("No book with ID:: " + request.bookId()));

        if (book.isArchived() || !book.isShareable())
            throw new OperationNotPermittedException("Not permitted to give feedback the book since it is archived or not shareable");

        User user = (User) connectedUser.getPrincipal();

        if (Objects.equals(book.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException("Not permitted to give feedback the book that already owns you");
        }

        Feedback feedback = feedbackMapper.toFeedback(request);

        return feedbackRepository.save(feedback).getId();
    }

    public PageResponse<FeedbackResponse> findAllFeedbacksByBookId(Integer bookId, int page, int size, Authentication connectedUser) {
        Pageable pageable = PageRequest.of(page, size);
        User user = (User) connectedUser.getPrincipal();

        Page<Feedback> feedbacks = feedbackRepository.findAllFeedbacksByBookId(bookId, pageable);
        List<FeedbackResponse> feedbackResponse = feedbacks
                .map(feedback -> feedbackMapper.toFeedbackResponse(feedback,user.getId()))
                .toList();

        return new PageResponse<>(
                feedbackResponse,
                feedbacks.getNumber(),
                feedbacks.getSize(),
                feedbacks.getTotalElements(),
                feedbacks.getTotalPages(),
                feedbacks.isFirst(),
                feedbacks.isLast()

        );

    }
}
