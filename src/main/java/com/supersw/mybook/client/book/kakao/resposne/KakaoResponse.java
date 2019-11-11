package com.supersw.mybook.client.book.kakao.resposne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class KakaoResponse<T> {

    Meta meta;
    T documents;

}
