package com.supersw.mybook.client.book;

import com.supersw.mybook.book.domain.Book;
import com.supersw.mybook.client.book.kakao.resposne.KakaoBookSearchResponse;
import com.supersw.mybook.client.book.naver.response.NaverBookSearchResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BookSearchClientResponse {

    private final List<Book> books;
    private final Page page;

    public static BookSearchClientResponse of(KakaoBookSearchResponse kakaoBookSearchResponse) {
        return new BookSearchClientResponse(kakaoBookSearchResponse.getDocuments().stream().map(Book::of).collect(Collectors.toList()), Page.of(kakaoBookSearchResponse.getMeta()));
    }

    public static BookSearchClientResponse of(NaverBookSearchResponse naverBookSearchResponse) {
        return new BookSearchClientResponse(naverBookSearchResponse.getItems().stream().map(Book::of).collect(Collectors.toList()),
                                            Page.of(naverBookSearchResponse));
    }

}
