package com.supersw.mybook.client.book.naver.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.supersw.mybook.client.book.naver.response.NaverBookSearchResponse.NaverBookSearchResponseData;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;

public class NaverBookSearchResponse extends NaverResponse<NaverBookSearchResponseData.Item> {

    @Getter
    public static class NaverBookSearchResponseData {

        @Getter
        private List<Item> items;

        @Getter
        public static class Item {
            private String title;
            private String link;
            private String image;
            private String author;
            private int price;
            private int discount;
            private String publisher;
            private String isbn;
            private String description;
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
            private LocalDate pubdate;
        }
    }

}
