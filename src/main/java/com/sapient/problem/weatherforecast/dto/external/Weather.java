package com.sapient.problem.weatherforecast.dto.external;

import lombok.Value;

@Value
public class Weather {

    private String main;

    public boolean isRaining() {
        return "Rain".equalsIgnoreCase(this.main);
    }
}
