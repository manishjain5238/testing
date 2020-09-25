package com.sapient.problem.weatherforecast.http;


import static com.sapient.problem.weatherforecast.constant.Constants.VERSION;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicate;

/**
 * The Request Predicate class contains checks required for request routing.
 */
public class RequestPredicates {

    private RequestPredicates() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Checks for Accept HTTP header along with 'version' parameter.
     *
     * @param mediaType the media type against the request needs to be checked
     * @return Predicate with result
     */
    public static RequestPredicate acceptWithVersion(MediaType mediaType) {
        return org.springframework.web.reactive.function.server.RequestPredicates.accept(mediaType)
                        .and(request -> matchesVersion(request.headers()
                                        .accept(), mediaType));
    }

    /**
     * Checks for Content-Type HTTP header along with 'version' parameter.
     *
     * @param mediaType the media type against the request needs to be checked
     * @return Predicate with result
     */
    public static RequestPredicate contentTypeWithVersion(MediaType mediaType) {
        return org.springframework.web.reactive.function.server.RequestPredicates.contentType(mediaType)
                        .and(request -> checkVersion(request.headers()
                                        .contentType()
                                        .get(), mediaType));
    }

    private static boolean matchesVersion(List<MediaType> mediaTypes, MediaType mediaType) {
        return mediaTypes.stream()
                        .anyMatch(acceptHeader -> checkVersion(acceptHeader, mediaType));
    }

    private static boolean checkVersion(MediaType requested, MediaType toBeChecked) {
        if (requested == null) {
            return false;
        }
        return toBeChecked.getParameter(VERSION)
                        .equalsIgnoreCase(requested.getParameter(VERSION));
    }
}
