package com.publicissapient.weatherforecast.controller;

import com.publicissapient.weatherforecast.model.ResponseBO;
import com.publicissapient.weatherforecast.service.WeatherForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Rest Controller class for Weather Forecast.
 */
@RestController
@RequestMapping("/weatherForecast")
public class WeatherForecastController {

    @Autowired
    private WeatherForecastService weatherForecastService;

    /**
     * Get Temperature and Rain condition of the City passed.
     *
     * @param cityName
     * @return @{@link ResponseBO}
     */
    @GetMapping("/getWeatherByCityName/{cityName}")
    public ResponseBO getCityWeatherForecastByCityName(@PathVariable String cityName){
       return weatherForecastService.getCityWeatherForecast(cityName);
    }
}
