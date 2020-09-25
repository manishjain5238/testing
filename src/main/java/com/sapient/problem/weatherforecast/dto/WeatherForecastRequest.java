package com.sapient.problem.weatherforecast.dto;

import static com.sapient.problem.weatherforecast.constant.ValidationMessages.NOT_BLANK;
import static com.sapient.problem.weatherforecast.constant.ValidationMessages.SPECIAL_CHARACTER;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeatherForecastRequest {

    @NotBlank(message = NOT_BLANK)
    @Pattern(regexp = "^[a-zA-Z]*$", message = SPECIAL_CHARACTER)
    String country;

    @NotBlank(message = NOT_BLANK)
    @Pattern(regexp = "^[a-zA-Z]*$", message = SPECIAL_CHARACTER)
    String city;
}
