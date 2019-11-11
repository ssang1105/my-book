package com.supersw.mybook.book.repository;

import com.supersw.mybook.book.domain.BookSearchHistory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookSearchHistoryRepository extends JpaRepository<BookSearchHistory, Long> {

    List<BookSearchHistory> findAll();
    List<BookSearchHistory> findByMemberId(String memberId);
}