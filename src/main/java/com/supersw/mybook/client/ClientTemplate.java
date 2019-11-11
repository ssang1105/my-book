package com.supersw.mybook.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supersw.mybook.client.exceptions.ClientException;
import java.io.IOException;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;

public abstract class ClientTemplate<T> {

    public abstract ObjectMapper getObjectMapper();

    protected <U extends T> U execute(Request request, TypeReference<U> type) {
        initRequestHeader(request);

        try {
            HttpResponse response = request.execute().returnResponse();
            String content = EntityUtils.toString(response.getEntity());

            checkStatusCode(response, request, content);

            return getObjectMapper().readValue(content, type);
        } catch (IOException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }

    private void checkStatusCode(HttpResponse response, Request request, String content) {
        if (response.getStatusLine().getStatusCode() >= 400) {
            throw new ClientException(response.getStatusLine().getStatusCode(), request.toString(), content);
        }
    }

    protected void initRequestHeader(Request request) {
        request.addHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
    }

}