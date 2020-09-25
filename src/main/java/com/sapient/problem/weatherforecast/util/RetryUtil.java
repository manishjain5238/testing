package com.sapient.problem.weatherforecast.util;

import java.time.Duration;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import lombok.extern.slf4j.Slf4j;
import reactor.util.retry.Retry;

@Slf4j
@Data
@AllArgsConstructor
public class RetryUtil {

    int retryAttemptCount;

    int backOffDurationInMills;

    /**
     * This method will provide Retry object based on passed attempt count and backoffDuration in
     * millis. It will internally used jitter exponential backoff logic.
     * 
     * @return Retry
     */
    public Retry getRetryObj() {
        return Retry.backoff(retryAttemptCount, Duration.ofMillis(backOffDurationInMills))
                        .filter(value ->
                            {
                                if (value.getClass() == WebClientResponseException.BadRequest.class
                                                || value.getClass() == WebClientResponseException.Forbidden.class
                                                || value.getClass() == WebClientResponseException.NotFound.class) {
                                    log.info("Retry will not be applied for exception : {}", value.getClass());
                                    return false;
                                }
                                return true;
                            })
                        .doAfterRetry(data -> log.error("Getting exception after each retry: {}", data));
    }
}
