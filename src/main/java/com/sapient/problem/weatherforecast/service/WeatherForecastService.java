package com.sapient.problem.weatherforecast.service;

import static com.sapient.problem.weatherforecast.convertor.WeatherResponseConverter.buildResponse;

import org.springframework.stereotype.Service;

import com.sapient.problem.weatherforecast.dto.WeatherForecastRequest;
import com.sapient.problem.weatherforecast.dto.WeatherForecastResponse;
import com.sapient.problem.weatherforecast.dto.external.ExternalWeatherAPIResponse;
import com.sapient.problem.weatherforecast.service.external.ExternalWeatherService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class WeatherForecastService {

    private final ExternalWeatherService externalWeatherService;

    public Mono<WeatherForecastResponse> getWeatherForecast(WeatherForecastRequest weatherForecastRequest) {

        return getWeatherData(weatherForecastRequest)
                        .map(apiResponse -> buildResponse(weatherForecastRequest, apiResponse));
    }

    private Mono<ExternalWeatherAPIResponse> getWeatherData(WeatherForecastRequest weatherForecastRequest) {
        return externalWeatherService.getWeatherData(weatherForecastRequest);
    }

}
