package com.sapient.problem.weatherforecast.util;

import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.HashMap;
import java.util.Map;

public class HttpUtils {

    public static final String APPLICATION_JSON_V1_VALUE = "application/vnd.sapient.api+json;version=1";

    private HttpUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Generates the headers for service calls.
     *
     * @return the HTTP headers
     */
    public static Map<String, String> createHeaders(org.springframework.http.MediaType mediaType) {
        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.CONTENT_TYPE, mediaType.toString());
        return headers;
    }

    public static final org.springframework.http.MediaType APPLICATION_JSON_V1 =
                    org.springframework.http.MediaType.valueOf(APPLICATION_JSON_V1_VALUE);

    public static ServerResponse.BodyBuilder okResponseV1() {
        return ServerResponse.ok()
                        .header("Content-Type", APPLICATION_JSON_V1_VALUE);
    }
}
