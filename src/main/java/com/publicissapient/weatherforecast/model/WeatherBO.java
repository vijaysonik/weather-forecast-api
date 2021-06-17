package com.publicissapient.weatherforecast.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

/**
 * BO class to hold Weather Detail for city.
 *
 * @author Vijay Soni
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherBO {

    private String date;
    private BigDecimal minimumTemp;
    private BigDecimal maximumTemp;
    private String weatherStatus;
    private String message;
}
