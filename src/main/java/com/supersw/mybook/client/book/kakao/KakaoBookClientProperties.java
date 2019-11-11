package com.supersw.mybook.client.book.kakao;

import com.supersw.mybook.client.ClientProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "kakao.book-client")
public class KakaoBookClientProperties extends ClientProperties {

    private String appKey;
}
