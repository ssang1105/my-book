package com.supersw.mybook.config;

import com.supersw.mybook.client.book.kakao.KakaoBookClientProperties;
import com.supersw.mybook.client.book.naver.NaverBookClientProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties({KakaoBookClientProperties.class, NaverBookClientProperties.class})
public class WebConfig implements WebMvcConfigurer {


}
