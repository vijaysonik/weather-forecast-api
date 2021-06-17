package com.publicissapient.weatherforecast.helper;

import com.publicissapient.weatherforecast.generated.Weatherforecast;
import com.publicissapient.weatherforecast.model.CityBO;
import com.publicissapient.weatherforecast.model.ResponseBO;
import com.publicissapient.weatherforecast.model.StatusBO;
import com.publicissapient.weatherforecast.model.WeatherBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static com.publicissapient.weatherforecast.helper.Constant.*;

/**
 * Data Mapper class is Helper class to convert DO to BO
 */
@Component
public class DataMapper {

    @Autowired
    private FactoryHelper factoryHelper;

    /**
     * Processing the JSON resposne received from System API.
     *
     * @param weatherforecast
     * @return
     */
    public ResponseBO processAPIResponse(Weatherforecast weatherforecast) {

        ResponseBO responseBO = factoryHelper.createResponseBO();

        if (null != weatherforecast.getAdditionalProperties() && !weatherforecast.getAdditionalProperties().isEmpty()){
            StatusBO statusBO = new StatusBO();
            Object statusCode = weatherforecast.getAdditionalProperties().get("statusCode");
            Object statusTxt =  weatherforecast.getAdditionalProperties().get("statusMsg");

            statusBO.setStatusCode(null==statusCode?"SUCCESS":statusCode.toString());
            statusBO.setStatusTxt(null==statusTxt?"SUCCESS":statusTxt.toString());
            responseBO.setStatus(statusBO);
        }

        CityBO cityBO = factoryHelper.createCityBO();

        List<WeatherBO> weatherBOList = new ArrayList<>();
        LinkedHashMap<String, BigDecimal> minTempMap = new LinkedHashMap<>();
        LinkedHashMap<String, BigDecimal> maxTempMap = new LinkedHashMap<>();
        LinkedHashMap<String, String> statusMap = new LinkedHashMap<>();
        Set<String> dateList = new HashSet<>();

        //extract the Response and set in BO object
        extractWeatherResponse(weatherforecast, cityBO, minTempMap, maxTempMap, statusMap, dateList);

        //populate WeatherBO object , iterate over Date captured and all values
        populateWeatherBO(weatherBOList, minTempMap, maxTempMap, statusMap, dateList);

        Collections.sort(weatherBOList, new Comparator<WeatherBO>() {
            @Override
            public int compare(WeatherBO o1, WeatherBO o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        responseBO.setCity(cityBO);
        responseBO.setWeatherList(weatherBOList);
        return responseBO;

    }

    private void populateWeatherBO(List<WeatherBO> weatherBOList, LinkedHashMap<String, BigDecimal> minTempMap, LinkedHashMap<String, BigDecimal> maxTempMap, LinkedHashMap<String, String> statusMap, Set<String> dateList) {
        for (String date : dateList) {
            WeatherBO weatherBO = factoryHelper.createWeatherBO();
            weatherBO.setDate(date);
            weatherBO.setMinimumTemp(convertKelvinToCelsius(minTempMap.get(date)));
            weatherBO.setMaximumTemp(convertKelvinToCelsius(maxTempMap.get(date)));
            weatherBO.setWeatherStatus(statusMap.get(date));
            weatherBO.setMessage(populateStatus(statusMap.get(date), maxTempMap.get(date)));
            weatherBOList.add(weatherBO);
        }
    }

    /**
     * Private Method to Extract the Response.
     *
     * @param weatherforecast
     * @param cityBO
     * @param minTempMap
     * @param maxTempMap
     * @param statusMap
     * @param dateList
     */
    private void extractWeatherResponse(Weatherforecast weatherforecast, CityBO cityBO, LinkedHashMap<String, BigDecimal> minTempMap, LinkedHashMap<String, BigDecimal> maxTempMap, LinkedHashMap<String, String> statusMap, Set<String> dateList) {
        if (null != weatherforecast) {
            if (null != weatherforecast.getCity()) {
                cityBO.setCityName(weatherforecast.getCity().getCityName());
                cityBO.setCountryName(weatherforecast.getCity().getCountryName());
            }

            if (null != weatherforecast.getWeatherList() && !weatherforecast.getWeatherList().isEmpty()) {
                weatherforecast.getWeatherList().stream().forEach(x -> {
                    LinkedHashMap inMap = (LinkedHashMap) x;

                    String dateStr = String.valueOf(inMap.get("dtString"));
                    String dateSubStr = dateStr.substring(0, 10);
                    BigDecimal minTemp = BigDecimal.valueOf((Double) inMap.get("minTemp"));
                    BigDecimal maxTemp = BigDecimal.valueOf((Double) inMap.get("maxTemp"));
                    String weatherStatus = String.valueOf(inMap.get("weatherStatus"));
                    if (minTempMap.containsKey(dateSubStr)) {
                        minTemp = (minTempMap.get(dateSubStr)).min(minTemp);
                    }
                    minTempMap.put(dateSubStr, minTemp);

                    if (maxTempMap.containsKey(dateSubStr)) {
                        maxTemp = (maxTempMap.get(dateSubStr)).max(maxTemp);
                    }
                    maxTempMap.put(dateSubStr, maxTemp);
                    statusMap.put(dateSubStr, weatherStatus);
                    dateList.add(dateSubStr);

                });
            }
        }
    }

    /**
     * Populate the Sttus Message to User based on Condition.
     * 1. If Temperature is More than 40 DEGREE Celcius then shoe Message = Use sunscreen lotion
     * 2. If Rain is expected then show Message = Carry Umbrella
     * 3. For all other - Show Default MEssage
     *
     * @param status
     * @param maxTemp
     * @return
     */
    private String populateStatus(String status, BigDecimal maxTemp) {
        return (status.contains(RAIN) ? MSG_CARRY_UMBRELLA : ((maxTemp.compareTo(FOURTY_DEGREE_KELVIN) > 0) ? MSG_USE_SUNSCREEN : DEFAULT_MSG));
    }


    /**
     * Utility Method to convert Temperature from Kelvin to Celsius.
     *
     * @param kelvin
     * @return
     */
    private BigDecimal convertKelvinToCelsius(BigDecimal kelvin) {
        return kelvin.subtract(BigDecimal.valueOf(KELVIN_CONST)).setScale(2, RoundingMode.HALF_DOWN);
    }
}
