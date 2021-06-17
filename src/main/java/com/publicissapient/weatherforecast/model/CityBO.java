package com.publicissapient.weatherforecast.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * BO class to hold CityBO Information.
 * Attributes : Name, Latitude, Longitude, Country Code, List of Weathers for next X Days.
 *
 * @author : Vijay Soni
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityBO {

    private String cityName;
    private String countryName;

}
