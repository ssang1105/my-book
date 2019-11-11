package com.supersw.mybook.client.book;

import com.supersw.mybook.client.book.request.BookSearchParam;

public interface BookClient {

    BookSearchClientResponse searchBooks(BookSearchParam param);
}
