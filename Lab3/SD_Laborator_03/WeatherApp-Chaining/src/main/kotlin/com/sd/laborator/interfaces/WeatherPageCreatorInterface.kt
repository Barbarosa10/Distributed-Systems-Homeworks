package com.sd.laborator.interfaces

import com.sd.laborator.pojo.WeatherForecastData

interface WeatherPageCreatorInterface {
    fun initPage()
    fun finishPage()
    fun returnPage() : String
    fun addElementToPage(element : String)
    fun  fillPage(forecastData: WeatherForecastData)
}