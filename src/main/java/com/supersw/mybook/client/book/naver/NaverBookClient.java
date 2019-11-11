package com.supersw.mybook.client.book.naver;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supersw.mybook.client.ClientTemplate;
import com.supersw.mybook.client.book.BookClient;
import com.supersw.mybook.client.book.BookSearchClientResponse;
import com.supersw.mybook.client.book.naver.response.NaverResponse;
import com.supersw.mybook.client.book.naver.response.NaverBookSearchResponse;
import com.supersw.mybook.client.book.request.BookSearchParam;
import java.net.URI;
import lombok.Getter;
import lombok.NonNull;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;

public class NaverBookClient extends ClientTemplate<NaverResponse> implements BookClient {

    private final int connectTimeout;
    private final int socketTimeout;
    private final String clientId;
    private final String clientSecret;
    @Getter
    private final ObjectMapper objectMapper;

    private final URI baseUri;
    private static final String searchBookUri = "/v1/search/book.json";

    private static final TypeReference<NaverBookSearchResponse> naverSearchBookResponseTypeReference = new TypeReference<NaverBookSearchResponse>() {
    };


    public NaverBookClient(@NonNull ObjectMapper objectMapper, @NonNull NaverBookClientProperties properties) {
        this.objectMapper = objectMapper;
        this.connectTimeout = properties.getConnectTimeout();
        this.socketTimeout = properties.getSocketTimeout();
        this.clientId = properties.getClientId();
        this.clientSecret = properties.getClientSecret();
        this.baseUri = properties.getBaseUri();
    }

    @Override
    public BookSearchClientResponse searchBooks(BookSearchParam param) {

        String uri = new URIBuilder(baseUri).setPath(searchBookUri)
                                            .addParameter("query", param.getQuery())
                                            .addParameter("start", String.valueOf(param.getPage() * param.getSize()))
                                            .addParameter("display", String.valueOf(param.getSize()))
                                            .toString();
        Request request = Request.Get(uri)
                                 .addHeader("X-Naver-Client-Id", this.clientId)
                                 .addHeader("X-Naver-Client-Secret", this.clientSecret);

        NaverBookSearchResponse response = execute(request, naverSearchBookResponseTypeReference);
        return BookSearchClientResponse.of(response);
    }

    @Override
    protected void initRequestHeader(Request request) {
        super.initRequestHeader(request);
        request.connectTimeout(this.connectTimeout)
               .socketTimeout(this.socketTimeout);
    }

}
