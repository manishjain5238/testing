package com.sapient.problem.weatherforecast.handler;

import static com.sapient.problem.weatherforecast.util.HttpUtils.okResponseV1;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.sapient.problem.weatherforecast.dto.WeatherForecastRequest;
import com.sapient.problem.weatherforecast.handler.validator.RequestValidator;
import com.sapient.problem.weatherforecast.service.WeatherForecastService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class WeatherForecastHandler {

    private final WeatherForecastService weatherForecastService;
    private final RequestValidator requestValidator;

    public Mono<ServerResponse> getWeatherForecast(ServerRequest request) {
        return request.bodyToMono(WeatherForecastRequest.class)
                        .flatMap(requestValidator::validate)
                        .flatMap(weatherForecastService::getWeatherForecast)
                        .flatMap(response -> okResponseV1().bodyValue(response));
    }

}
