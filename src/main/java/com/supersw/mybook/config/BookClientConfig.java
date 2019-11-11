package com.supersw.mybook.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.supersw.mybook.client.book.kakao.KakaoBookClient;
import com.supersw.mybook.client.book.kakao.KakaoBookClientProperties;
import com.supersw.mybook.client.book.naver.NaverBookClient;
import com.supersw.mybook.client.book.naver.NaverBookClientProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BookClientConfig {

    private final ObjectMapper objectMapper;

    @Bean
    public KakaoBookClientProperties kakaoBookClientProperties() {
        return new KakaoBookClientProperties();
    }

    @Bean
    public KakaoBookClient kakaoBookClient() {
        return new KakaoBookClient(objectMapper, kakaoBookClientProperties());
    }


    @Bean
    public NaverBookClientProperties naverBookClientProperties() {
        return new NaverBookClientProperties();
    }

    @Bean
    public NaverBookClient naverBookClient() {
        return new NaverBookClient(objectMapper, naverBookClientProperties());
    }

}
