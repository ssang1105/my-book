package com.supersw.mybook.client.book.naver;

import com.supersw.mybook.client.ClientProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "naver.book-client")
public class NaverBookClientProperties extends ClientProperties {

    private String clientId;
    private String clientSecret;

}
