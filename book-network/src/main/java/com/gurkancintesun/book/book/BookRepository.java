package com.gurkancintesun.book.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book> {

    @Query("""
            SELECT b FROM Book b
                WHERE b.archived= false
                AND b.shareable= true
                AND b.id != :userId
            """)
    Page<Book> findAllDisplayableBooks(Pageable pageable, Integer userId);

}
