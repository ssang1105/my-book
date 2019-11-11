package com.supersw.mybook.client.book.naver.response;

import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class NaverResponse<T> {

    private int total;
    private int start;
    private int display;
    private List<T> items;

    public boolean isEnd() {
        return this.total <= this.start + this.display;
    }


}
