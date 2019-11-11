package com.supersw.mybook.client.book.kakao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supersw.mybook.client.ClientTemplate;
import com.supersw.mybook.client.book.BookClient;
import com.supersw.mybook.client.book.BookSearchClientResponse;
import com.supersw.mybook.client.book.kakao.resposne.KakaoResponse;
import com.supersw.mybook.client.book.kakao.resposne.KakaoBookSearchResponse;
import com.supersw.mybook.client.book.request.BookSearchParam;
import java.net.URI;
import lombok.Getter;
import lombok.NonNull;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;

public class KakaoBookClient extends ClientTemplate<KakaoResponse> implements BookClient {

    private final int connectTimeout;
    private final int socketTimeout;
    private final String appKey;
    @Getter
    private final ObjectMapper objectMapper;

    private final URI baseUri;
    private static final String searchBookUri = "/v3/search/book";

    private static final TypeReference<KakaoBookSearchResponse> kakaoSearchBookResponseTypeReference = new TypeReference<KakaoBookSearchResponse>() {
    };


    public KakaoBookClient(@NonNull ObjectMapper objectMapper, @NonNull KakaoBookClientProperties properties) {
        this.objectMapper = objectMapper;
        this.connectTimeout = properties.getConnectTimeout();
        this.socketTimeout = properties.getSocketTimeout();
        this.appKey = properties.getAppKey();
        this.baseUri = properties.getBaseUri();
    }

    @Override
    public BookSearchClientResponse searchBooks(BookSearchParam param) {
        String uri = new URIBuilder(baseUri).setPath(searchBookUri)
                                            .addParameter("query", param.getQuery())
                                            .addParameter("page", String.valueOf(param.getPage()))
                                            .addParameter("size", String.valueOf(param.getSize()))
                                            .toString();
        Request request = Request.Get(uri)
                                 .addHeader("Authorization", "KakaoAK " + appKey);
        KakaoBookSearchResponse response = execute(request, kakaoSearchBookResponseTypeReference);
        return BookSearchClientResponse.of(response);
    }

    @Override
    protected void initRequestHeader(Request request) {
        super.initRequestHeader(request);
        request.connectTimeout(this.connectTimeout)
               .socketTimeout(this.socketTimeout);
    }

}
