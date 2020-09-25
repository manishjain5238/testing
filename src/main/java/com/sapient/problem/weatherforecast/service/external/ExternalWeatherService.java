package com.sapient.problem.weatherforecast.service.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.sapient.problem.weatherforecast.constant.Constants;
import com.sapient.problem.weatherforecast.dto.WeatherForecastRequest;
import com.sapient.problem.weatherforecast.dto.external.ExternalWeatherAPIResponse;
import com.sapient.problem.weatherforecast.exception.ServiceInvocationException;
import com.sapient.problem.weatherforecast.http.WebClientUtil;
import com.sapient.problem.weatherforecast.util.ErrorMessages;
import com.sapient.problem.weatherforecast.util.RetryUtil;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * The Class MasterDataService class for all service call to master data service, these calls are
 * external in nature, made with the help of {@link WebClientUtil}.
 */
@Slf4j
@Service
public class ExternalWeatherService {

    @Value(Constants.EXTERNAL_WEATHER_SERVICE_SCHEME)
    private String scheme;

    @Value(Constants.EXTERNAL_WEATHER_SERVICE_HOST)
    private String host;

    @Value(Constants.EXTERNAL_WEATHER_SERVICE_PATH)
    private String path;

    @Value(Constants.EXTERNAL_WEATHER_SERVICE_APPID)
    private String appid;

    @Value(Constants.EXTERNAL_WEATHER_SERVICE_MODE)
    private String mode;

    @Value(Constants.EXTERNAL_WEATHER_SERVICE_UNITS)
    private String units;

    @Autowired
    private WebClientUtil webClientUtil;

    @Autowired
    private RetryUtil retryUtil;

    public Mono<ExternalWeatherAPIResponse> getWeatherData(WeatherForecastRequest weatherForecastRequest) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("appid", appid);
        queryParams.add("units", units);
        queryParams.add("mode", mode);
        queryParams.add("q", weatherForecastRequest.getCity() + "," + weatherForecastRequest.getCountry());

        return webClientUtil.doGetCall(scheme, host, path, queryParams, null, retryUtil)
                        .onErrorMap(e -> new ServiceInvocationException(HttpStatus.INTERNAL_SERVER_ERROR,
                                        ErrorMessages.SYSTEM_ERROR, e));
    }
}
