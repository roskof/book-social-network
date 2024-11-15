package com.gurkancintesun.book.history;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookTransactionHistoryRepository extends JpaRepository<BookTransactionHistory, Integer> {

    @Query("""
            SELECT history FROM BookTransactionHistory history
                WHERE history.user.id = :userId
            """)
    Page<BookTransactionHistory> findAllBorrowedBooks(Pageable pageable, Integer userId);

    @Query("""
            SELECT history FROM BookTransactionHistory history
                WHERE history.book.owner.id = :userId
            """)
    Page<BookTransactionHistory> findAllReturnedBooks(Pageable pageable, Integer userId);


    @Query("""
            SELECT (COUNT(*)>0) AS isBorrowed FROM BookTransactionHistory bookTransactionHistory
            WHERE bookTransactionHistory.user.id = :userId
            AND bookTransactionHistory.book.id = :bookId
            AND bookTransactionHistory.returnedApproved = false
            """)
    boolean isAlreadyBorrowedByUser(Integer bookId, Integer userId);

    @Query("""
            SELECT bookTransactionHistory FROM BookTransactionHistory bookTransactionHistory
            WHERE bookTransactionHistory.user.id = :userId
            AND bookTransactionHistory.book.id = :bookId
            AND bookTransactionHistory.returned = false
            AND bookTransactionHistory.returnedApproved = false
            """)
    Optional<BookTransactionHistory> findReturnBorrowedByBookIdAndUserId(Integer bookId, Integer userId);

    @Query("""
            SELECT bookTransactionHistory FROM BookTransactionHistory bookTransactionHistory
            WHERE bookTransactionHistory.book.owner.id = :userId
            AND bookTransactionHistory.book.id = :bookId
            AND bookTransactionHistory.returned = true
            AND bookTransactionHistory.returnedApproved = false
            """)
    Optional<BookTransactionHistory> findByBookIdAndOwnerId(Integer bookId, Integer userId);
}
