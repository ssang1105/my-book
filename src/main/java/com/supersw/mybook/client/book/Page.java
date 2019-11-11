package com.supersw.mybook.client.book;


import com.supersw.mybook.client.book.kakao.resposne.Meta;
import com.supersw.mybook.client.book.naver.response.NaverResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Page {

    private boolean isEnd;

    public static Page of(Meta meta) {
        return new Page(meta.isEnd());
    }

    public static Page of(NaverResponse naverResponse) {
        return new Page(naverResponse.isEnd());
    }
}
