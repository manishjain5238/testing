package com.sapient.problem.weatherforecast.router;

import static com.sapient.problem.weatherforecast.http.RequestPredicates.acceptWithVersion;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.sapient.problem.weatherforecast.constant.Constants;
import com.sapient.problem.weatherforecast.handler.WeatherForecastHandler;
import com.sapient.problem.weatherforecast.util.HttpUtils;

@Configuration
public class WeatherForecastRouter {

    @Bean
    public RouterFunction<ServerResponse> router(WeatherForecastHandler weatherForecastHandler) {

        return nest(path(Constants.SAPIENT_API_URL).and(acceptWithVersion(HttpUtils.APPLICATION_JSON_V1)),
                        route(POST(Constants.FORECAST_URL), weatherForecastHandler::getWeatherForecast));
    }
}
