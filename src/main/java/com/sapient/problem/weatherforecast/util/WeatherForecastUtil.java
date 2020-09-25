package com.sapient.problem.weatherforecast.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import lombok.experimental.UtilityClass;

@UtilityClass
public class WeatherForecastUtil {

    /**
     * Generates the header map required for HTTP calls.
     *
     * @param mediaType the {@link MediaType}
     * @return the {@link Map} of HTTP headers
     */
    public static Map<String, String> createHeaders(org.springframework.http.MediaType mediaType) {
        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.CONTENT_TYPE, mediaType.toString());
        return headers;
    }

}
