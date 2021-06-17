package com.publicissapient.weatherforecast.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * BO class to hold final Response from System API.
 *
 * @author Vijay Soni
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBO {

    private CityBO city;
    private List<WeatherBO> weatherList;
    private StatusBO status;

}
