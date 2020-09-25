package com.sapient.problem.weatherforecast.convertor;

import java.util.List;
import java.util.stream.Collectors;

import com.sapient.problem.weatherforecast.dto.ForecastDetails;
import com.sapient.problem.weatherforecast.dto.WeatherForecastRequest;
import com.sapient.problem.weatherforecast.dto.WeatherForecastResponse;
import com.sapient.problem.weatherforecast.dto.external.ExternalWeatherAPIResponse;
import com.sapient.problem.weatherforecast.dto.external.Forecast;
import com.sapient.problem.weatherforecast.dto.external.Weather;

import lombok.experimental.UtilityClass;

@UtilityClass
public class WeatherResponseConverter {
    public static WeatherForecastResponse buildResponse(WeatherForecastRequest req,
                    ExternalWeatherAPIResponse apiResponse) {

        List<ForecastDetails> forecasts = apiResponse.getList()
                        .stream()
                        .map(WeatherResponseConverter::toForecastDetails)
                        .collect(Collectors.toList());

        return new WeatherForecastResponse(req.getCountry(), req.getCity(), forecasts);

    }

    private static ForecastDetails toForecastDetails(Forecast forecast) {
        return new ForecastDetails(forecast.getDt(), forecast.getMain()
                        .getTemp_min(),
                        forecast.getMain()
                                        .getTemp_max(),
                        getForecastComments(forecast.getWeather(), forecast.getMain()
                                        .getTemp_max()));
    }

    private static String getForecastComments(List<Weather> allWeather, double maxTemp) {
        List<Boolean> isRaining = allWeather.stream()
                        .map(weather -> weather.isRaining() ? true : false)
                        .collect(Collectors.toList());
        if (isRaining.contains(true)) {
            return "Carry umbrella";
        } else if (maxTemp > 35.0) {
            return "Use sunscreen lotion";
        } else {
            return null;
        }
    }


}
