package com.sapient.problem.weatherforecast.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Value;

@Value
public class ForecastDetails {

    private long date;
    private double minTemp;
    private double maxTemp;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String comment;
}
