package com.supersw.mybook.config;

import java.time.Duration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.CookieGenerator;

@Configuration
public class CookieGeneratorConfig {

    public static final String MY_BOOK_SESSION_ID = "my-book-session-id";

    @Bean
    public CookieGenerator memberSessionCookieGenerator() {
        CookieGenerator cookieGenerator = new CookieGenerator();
        cookieGenerator.setCookieName(MY_BOOK_SESSION_ID);
        cookieGenerator.setCookieMaxAge((int) Duration.ofDays(30L).getSeconds());
        return cookieGenerator;
    }


}
