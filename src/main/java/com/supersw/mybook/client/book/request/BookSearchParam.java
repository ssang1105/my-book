package com.supersw.mybook.client.book.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BookSearchParam {

    private final String query;
    private final int page;
    private final int size;


    public static BookSearchParam create(String query, int page, int size) {
        return new BookSearchParam(query, page, size);
    }

}
