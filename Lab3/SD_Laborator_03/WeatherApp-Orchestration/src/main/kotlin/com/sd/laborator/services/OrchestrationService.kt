package com.sd.laborator.services

import com.sd.laborator.interfaces.OrchestrationInterface
import com.sd.laborator.pojo.PositionData
import com.sd.laborator.pojo.WeatherForecastData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrchestrationService : OrchestrationInterface{
    @Autowired
    private  lateinit var  locationSearchService: LocationSearchService

    @Autowired
    private lateinit var  weatherForecastService: WeatherForecastService

    @Autowired
    private lateinit var  weatherPageCreatorService: WeatherPageCreatorService

    override fun getLocationId(locationName: String): PositionData? {
        return locationSearchService.getLocationId(locationName)
    }

    override fun getForecastData(locationId: PositionData): WeatherForecastData {
        return weatherForecastService.getForecastData(locationId)
    }

    override fun toHtml(weatherForecastData: WeatherForecastData): String {
        weatherPageCreatorService.initPage()
        weatherPageCreatorService.fillPage(weatherForecastData)
        weatherPageCreatorService.finishPage()

        return weatherPageCreatorService.returnPage()
    }

}