package com.sapient.problem.weatherforecast.dto.external;

import java.util.List;

import lombok.Value;

@Value
public class Forecast {

    private List<Weather> weather;
    private Temperature main;
    private long dt;

}

