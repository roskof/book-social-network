package com.gurkancintesun.book.book;

import com.gurkancintesun.book.common.BaseEntity;
import com.gurkancintesun.book.feedback.Feedback;
import com.gurkancintesun.book.history.BookTransactionHistory;
import com.gurkancintesun.book.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book extends BaseEntity {

    private String title;
    private String authorName;
    private String isbn;
    private String synopsis;
    private String bookCover;

    private boolean archived;
    private boolean shareable;

    @ManyToOne
    @JoinColumn(name = "owner_id") //default olarak set ediliyor...
    private User owner;

    @OneToMany(mappedBy = "book")
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "book")
    private List<BookTransactionHistory> histories;

    @Transient
    public double getRate() {
        if (feedbacks == null || feedbacks.isEmpty()) return 0.0;

        var rate = feedbacks.stream()
                .mapToDouble(Feedback::getNote)
                .average()
                .orElse(0.0);

        return Math.round(rate * 10.0) / 10.0;
    }
}
