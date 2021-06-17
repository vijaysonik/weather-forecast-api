package com.publicissapient.weatherforecast.helper;

import com.publicissapient.weatherforecast.model.CityBO;
import com.publicissapient.weatherforecast.model.ResponseBO;
import com.publicissapient.weatherforecast.model.WeatherBO;
import org.springframework.stereotype.Component;

/**
 * Factory helper to create new Object.
 */
@Component
public final class FactoryHelper {

    /**
     * @return ResponseBO
     */
    public ResponseBO createResponseBO(){
        return  new ResponseBO();
    }

    /**
     * @return CityBO
     */
    public CityBO createCityBO() {
        return new CityBO();
    }

    /**
     * @return WeatherBO
     */
    public WeatherBO createWeatherBO() {
        return new WeatherBO();
    }
}
