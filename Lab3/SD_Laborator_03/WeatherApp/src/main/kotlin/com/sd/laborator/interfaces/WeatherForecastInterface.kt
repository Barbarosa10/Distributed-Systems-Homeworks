package com.sd.laborator.interfaces

import com.sd.laborator.pojo.PositionData
import com.sd.laborator.pojo.WeatherForecastData

interface WeatherForecastInterface {
    fun getForecastData(locationId: PositionData): WeatherForecastData
}