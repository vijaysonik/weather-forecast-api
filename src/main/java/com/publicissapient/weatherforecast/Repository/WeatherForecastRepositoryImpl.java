package com.publicissapient.weatherforecast.Repository;

import com.publicissapient.weatherforecast.generated.Weatherforecast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class WeatherForecastRepositoryImpl implements WeatherForecastRepository{

    @Value("${api.uri}")
    private String apiURI;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Weatherforecast callOpenWeatherAPI(String cityName) {

        final StringBuilder uri = new StringBuilder(apiURI).append(cityName);

        //Creating REST Template and Call API

        ResponseEntity<Weatherforecast> responseEntity = restTemplate.getForEntity(uri.toString(), Weatherforecast.class);

        //Collect Response Body in Map
        return responseEntity.getBody();
    }
}
