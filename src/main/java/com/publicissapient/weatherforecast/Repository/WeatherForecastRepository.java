package com.publicissapient.weatherforecast.Repository;

import com.publicissapient.weatherforecast.generated.Weatherforecast;

public interface WeatherForecastRepository {

    /**
     * call to System API to get City weather Data.
     *
     * @param cityName
     * @return
     */
    public Weatherforecast callOpenWeatherAPI(String cityName);
}
