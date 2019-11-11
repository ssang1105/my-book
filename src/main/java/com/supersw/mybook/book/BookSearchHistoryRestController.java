package com.supersw.mybook.book;

import com.supersw.mybook.book.domain.BookSearchHistory;
import com.supersw.mybook.book.domain.BookSearchHistorySummary;
import com.supersw.mybook.book.service.BookSearchHistoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/book-search-histories")
public class BookSearchHistoryRestController {

    private final BookSearchHistoryService bookSearchHistoryService;

    @GetMapping
    public List<BookSearchHistory> get(@RequestParam String memberId) {
        return bookSearchHistoryService.getSearchHistories(memberId);
    }

    @GetMapping("/summary")
    public List<BookSearchHistorySummary> getSummary() {
        return bookSearchHistoryService.getSummary();
    }

}
