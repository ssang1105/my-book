package com.supersw.mybook.book.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.supersw.mybook.client.book.kakao.resposne.KakaoBookSearchResponse.KakaoBookSearchResponseData;
import com.supersw.mybook.client.book.naver.response.NaverBookSearchResponse.NaverBookSearchResponseData.Item;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
@EqualsAndHashCode
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Book {

    private String title;
    private String contents;
    private String thumbnail;
    private String isbn;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime datetime;
    private List<String> authors;
    private String publisher;
    private int price;
    private int salePrice;

    public static Book of(KakaoBookSearchResponseData kakaoBook) {
        return Book.builder()
                   .title(kakaoBook.getTitle())
                   .contents(kakaoBook.getContents())
                   .thumbnail(kakaoBook.getThumbnail())
                   .isbn(kakaoBook.getIsbn())
                   .datetime(kakaoBook.getDatetime())
                   .authors(kakaoBook.getAuthors())
                   .publisher(kakaoBook.getPublisher())
                   .price(kakaoBook.getPrice())
                   .salePrice(kakaoBook.getSalePrice()).build();
    }

    public static Book of(Item naverBook) {
        return Book.builder()
                   .title(naverBook.getTitle())
                   .contents(naverBook.getDescription())
                   .thumbnail(naverBook.getImage())
                   .isbn(naverBook.getIsbn())
                   .datetime(naverBook.getPubdate().atStartOfDay())
                   .authors(Arrays.asList(naverBook.getAuthor()))
                   .publisher(naverBook.getPublisher())
                   .price(naverBook.getPrice())
                   .salePrice(naverBook.getDiscount())
                   .build();
    }


}
