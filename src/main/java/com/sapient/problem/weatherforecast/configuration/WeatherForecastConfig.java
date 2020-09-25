package com.sapient.problem.weatherforecast.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sapient.problem.weatherforecast.constant.Constants;
import com.sapient.problem.weatherforecast.http.WebClientUtil;
import com.sapient.problem.weatherforecast.util.RetryUtil;


@Configuration
public class WeatherForecastConfig {

    /** The retry attempt count. */
    @Value(Constants.EXTERNAL_WEATHER_SERVICE_RETRY)
    private int retryAttemptCount;

    /** The back off duration in mills. */
    @Value(Constants.EXTERNAL_WEATHER_SERVICE_RETRY_BACKOFF)
    private int backOffDurationInMills;

    /**
     * Instantiates the {@link WebClientUtil} bean.
     *
     * @return the {@link WebClientUtil} bean
     */
    @Bean
    public WebClientUtil getWebClientUtil() {
        return new WebClientUtil();
    }

    /**
     * Instantiates the {@link RetryUtil} bean..
     *
     * @return the retry util
     */
    @Bean
    public RetryUtil getRetryUtil() {
        return new RetryUtil(retryAttemptCount, backOffDurationInMills);
    }
}
