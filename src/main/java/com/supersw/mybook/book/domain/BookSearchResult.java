package com.supersw.mybook.book.domain;

import com.supersw.mybook.client.book.BookSearchClientResponse;
import com.supersw.mybook.client.book.Page;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BookSearchResult {

    private final List<Book> books;
    private final Page page;

    public static BookSearchResult of(BookSearchClientResponse response) {
        return new BookSearchResult(response.getBooks(), response.getPage());
    }

}
