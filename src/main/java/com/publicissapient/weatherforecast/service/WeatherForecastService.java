package com.publicissapient.weatherforecast.service;

import com.publicissapient.weatherforecast.Repository.WeatherForecastRepository;
import com.publicissapient.weatherforecast.generated.Weatherforecast;
import com.publicissapient.weatherforecast.helper.DataMapper;
import com.publicissapient.weatherforecast.model.ResponseBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for Open Weather API processing.
 */
@Service
public class WeatherForecastService {

    @Autowired
    private WeatherForecastRepository weatherForecastRepository;

    @Autowired
    private DataMapper mapper;

    /**
     * Service Method to fetch and Translate into Response BO Object.
     * @param cityName
     * @return
     */
    public ResponseBO getCityWeatherForecast(String cityName){
        Weatherforecast weatherforecast =  weatherForecastRepository.callOpenWeatherAPI(cityName);
        return mapper.processAPIResponse(weatherforecast);

    }

}
