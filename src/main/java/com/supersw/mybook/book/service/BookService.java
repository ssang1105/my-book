package com.supersw.mybook.book.service;

import com.supersw.mybook.book.domain.BookSearchResult;
import com.supersw.mybook.client.book.BookSearchClientResponse;
import com.supersw.mybook.client.book.kakao.KakaoBookClient;
import com.supersw.mybook.client.book.naver.NaverBookClient;
import com.supersw.mybook.client.book.request.BookSearchParam;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class BookService {

    private final KakaoBookClient kakaoBookClient;
    private final NaverBookClient naverBookClient;
    private final BookSearchHistoryService bookSearchHistoryService;


    public BookSearchResult searchBooks(@NonNull String query, int page, int size, String memberId) {
        BookSearchParam bookSearchParam = BookSearchParam.create(query, page, size);
        bookSearchHistoryService.save(query, memberId);
        BookSearchClientResponse bookSearchClientResponse = null;
        try {
            bookSearchClientResponse = kakaoBookClient.searchBooks(bookSearchParam);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            log.info("### KAKAO 책 API 가 실패하여 NAVER 책 API 를 호출합니다.");
            bookSearchClientResponse = naverBookClient.searchBooks(bookSearchParam);
        }
        return BookSearchResult.of(bookSearchClientResponse);
    }

}
