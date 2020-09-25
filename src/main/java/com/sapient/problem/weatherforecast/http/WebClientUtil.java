package com.sapient.problem.weatherforecast.http;

import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

import com.sapient.problem.weatherforecast.dto.external.ExternalWeatherAPIResponse;
import com.sapient.problem.weatherforecast.util.RetryUtil;

import reactor.core.publisher.Mono;

/**
 * 
 * @author MANISH This class is build to handle all kind of external http call. This will use Spring
 *         5 webclient to make external call.
 *
 */
public class WebClientUtil {

    /**
     * Call async web client for HTTP POST request but without authentication. This is temporary
     * method to call ISOM API, will be removed once iSOM API codebase is removed
     *
     * @param url the url
     * @param headers map <String,String> can be null or empty.
     * @param body request body pojo
     * @param retryUtil the retry util
     * @return Mono<ClientResponse>
     */
    public Mono<ClientResponse> doPostCall(String url, Map<String, String> headers, Object body, RetryUtil retryUtil) {
        if (StringUtils.isEmpty(url)) {
            throw new IllegalArgumentException("Service url can not be null or empty.");
        }
        return getWebClient(url, headers).flatMap(webClient -> webClient.post()
                        .bodyValue(body)
                        .exchange()
                        .retryWhen(retryUtil.getRetryObj()));
    }

    public Mono<ExternalWeatherAPIResponse> doGetCall(String scheme, String host, String path,
                    MultiValueMap<String, String> queryParams, Map<String, String> headers, RetryUtil retryUtil) {
        if (StringUtils.isEmpty(host)) {
            throw new IllegalArgumentException("Service url can not be null or empty.");
        }
        return WebClient.create()
                        .get()
                        .uri(builder -> builder.scheme(scheme)
                                        .host(host)
                                        .path(path)
                                        .queryParams(queryParams)
                                        .build())
                        .retrieve()
                        .bodyToMono(ExternalWeatherAPIResponse.class)
                        .retryWhen(retryUtil.getRetryObj());
    }


    private Mono<WebClient> getWebClient(String url, Map<String, String> headers) {
        Builder builder = WebClient.builder()
                        .baseUrl(url);

        addHeaders(builder, headers);
        return Mono.just(builder.build());
    }

    /**
     * Add HTTP headers if provided to request.
     *
     * @param builder HttpRequest.Builder
     * @param headerMap Map<String, String>
     * @return HttpRequest.Builder
     */
    private Builder addHeaders(Builder builder, Map<String, String> headerMap) {
        /* Add all MDC context headers for logging and tracing */
        Map<String, String> mdcMap = MDC.getCopyOfContextMap();
        if (!ObjectUtils.isEmpty(mdcMap)) {
            for (Entry<String, String> entry : mdcMap.entrySet()) {
                builder.defaultHeader(entry.getKey(), entry.getValue());
            }
        }
        if (!ObjectUtils.isEmpty(headerMap)) {
            for (Entry<String, String> entry : headerMap.entrySet()) {
                builder.defaultHeader(entry.getKey(), entry.getValue());
            }
        }
        if (headerMap == null || !headerMap.containsKey(HttpHeaders.CONTENT_TYPE)) {
            builder.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        }
        return builder;
    }
}
