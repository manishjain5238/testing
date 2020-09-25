package com.sapient.problem.weatherforecast.dto.external;

import java.util.List;

import lombok.Value;

@Value
public class ExternalWeatherAPIResponse {

    private List<Forecast> list;
}
