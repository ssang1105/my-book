package com.supersw.mybook.book.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookSearchHistorySummary {

    private final String keyword;
    private final long searchCount;

}
