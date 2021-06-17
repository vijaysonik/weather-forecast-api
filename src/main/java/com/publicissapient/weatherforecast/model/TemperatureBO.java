package com.publicissapient.weatherforecast.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * BO class to hold Temperature from System API.
 *
 * @author Vijay Soni
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemperatureBO {

    private BigDecimal minimumTemp;
    private BigDecimal maximumTemp;
}
