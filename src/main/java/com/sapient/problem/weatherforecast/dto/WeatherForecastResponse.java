package com.sapient.problem.weatherforecast.dto;

import java.util.List;

import lombok.Value;

@Value
public class WeatherForecastResponse {
    String country;
    String city;
    List<ForecastDetails> forecasts;

}
