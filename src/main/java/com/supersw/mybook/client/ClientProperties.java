package com.supersw.mybook.client;

import java.net.URI;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.client.utils.URIBuilder;

@Getter
@Setter
public class ClientProperties {


    private String scheme = "http";
    private String host;
    private int port = -1;
    private int socketTimeout = 10000;
    private int connectTimeout = 2000;

    public URI getBaseUri() {
        return URI.create(new URIBuilder().setScheme(this.scheme)
                                          .setHost(this.host)
                                          .setPort(this.port)
                                          .toString());
    }

}
