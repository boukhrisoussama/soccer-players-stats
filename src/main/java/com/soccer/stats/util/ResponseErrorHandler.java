package com.soccer.stats.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

@Slf4j
public class ResponseErrorHandler extends DefaultResponseErrorHandler {

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        log.info("-- Response Status: {}", response.getStatusCode());
        log.info("-- Response Headers: {}}", response.getHeaders());
        log.info("-- Response Body: {}}", new String(getResponseBody(response)));
        super.handleError(response);
    }

}
