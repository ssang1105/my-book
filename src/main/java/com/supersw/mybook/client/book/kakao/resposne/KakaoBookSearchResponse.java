package com.supersw.mybook.client.book.kakao.resposne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.supersw.mybook.client.book.kakao.resposne.KakaoBookSearchResponse.KakaoBookSearchResponseData;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

public class KakaoBookSearchResponse extends KakaoResponse<List<KakaoBookSearchResponseData>> {

    @Getter
    public static class KakaoBookSearchResponseData {

        private String title;
        private String contents;
        private String url;
        private String isbn;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        private LocalDateTime datetime;
        private List<String> authors;
        private String publisher;
        private List<String> translators;
        private int price;
        @JsonProperty("sale_price")
        private int salePrice;
        private String thumbnail;
        private String status;
    }


}
