package com.publicissapient.weatherforecast.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * BO class to hold Status Detail
 * Attributes : Statuscode and StatusTxt
 *
 * @author : Vijay Soni
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusBO {

    private String statusCode;
    private String statusTxt;
}
