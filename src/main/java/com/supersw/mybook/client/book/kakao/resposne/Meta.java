package com.supersw.mybook.client.book.kakao.resposne;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Meta {

    @JsonProperty("is_end")
    private boolean isEnd;
    @JsonProperty("pageable_count")
    private int pagableCount;
    @JsonProperty("total_count")
    private int totalCount;
}