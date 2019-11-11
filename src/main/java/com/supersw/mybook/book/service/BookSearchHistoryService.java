package com.supersw.mybook.book.service;

import com.supersw.mybook.book.domain.BookSearchHistory;
import com.supersw.mybook.book.domain.BookSearchHistorySummary;
import com.supersw.mybook.book.repository.BookSearchHistoryRepository;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class BookSearchHistoryService {

    private final BookSearchHistoryRepository bookSearchHistoryRepository;

    public BookSearchHistory save(String keyword, String memberId) {
        return bookSearchHistoryRepository.save(BookSearchHistory.create(keyword, memberId));
    }

    public List<BookSearchHistory> getSearchHistories(String memberId) {
        return bookSearchHistoryRepository.findByMemberId(memberId).stream()
                                          .sorted(Comparator.comparing(BookSearchHistory::getRegisteredDateTime).reversed())
                                          .collect(Collectors.toList());
    }

    public List<BookSearchHistorySummary> getSummary() {
        List<BookSearchHistory> histories = bookSearchHistoryRepository.findAll();
        Map<String, List<BookSearchHistory>> keywordGroup = histories.stream().collect(Collectors.groupingBy(BookSearchHistory::getKeyword, Collectors.toList()));
        return keywordGroup.entrySet()
                           .stream()
                           .map(entry -> new BookSearchHistorySummary(entry.getKey(), entry.getValue().size()))
                           .sorted(Comparator.comparing(BookSearchHistorySummary::getSearchCount).reversed())
                           .collect(Collectors.toList());
    }

}
