package com.sd.laborator.interfaces

import com.sd.laborator.pojo.PositionData
import com.sd.laborator.pojo.WeatherForecastData

interface FirewallInterface{
    fun getLocationId(locationName : String) : PositionData?
    fun getForecastData(locationId : PositionData) : WeatherForecastData
    fun toHtml(location : String) : String
}