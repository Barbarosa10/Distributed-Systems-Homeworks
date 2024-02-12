package com.sd.laborator.services

import com.sd.laborator.interfaces.WeatherForecastInterface
import com.sd.laborator.pojo.PositionData
import com.sd.laborator.pojo.WeatherForecastData
import org.json.JSONObject
import org.springframework.stereotype.Service
import java.net.URL
import kotlin.math.roundToInt

@Service
class WeatherForecastService (private val timeService: TimeService) : WeatherForecastInterface {
    override fun getForecastData(locationId: PositionData): WeatherForecastData {
        // ID-ul locaţiei nu trebuie codificat, deoarece este numeric
        val forecastDataURL = URL("https://api.open-meteo.com/v1/forecast?latitude=${locationId.latitude}&longitude=${locationId.longitude}" +
                "&current_weather=true&daily=temperature_2m_max,temperature_2m_min&timezone=auto&hourly=relativehumidity_2m")

        // preluare conţinut răspuns HTTP la o cerere GET către URL-ul de mai sus
        val rawResponse: String = forecastDataURL.readText()

        // parsare obiect JSON primit
        val responseRootObject = JSONObject(rawResponse)


        // construire şi returnare obiect POJO care încapsulează datele meteo
        val arr = listOf<String>("N", "NE", "E", "SE", "S", "SW", "W", "NW")
        return WeatherForecastData(
            location = locationId.location,
            date = timeService.getCurrentTime(),
//            weatherState = weatherDataObject.getString("weather_state_name"),
//            weatherStateIconURL =
//                "https://www.metaweather.com/static/img/weather/png/${weatherDataObject.getString("weather_state_abbr")}.png",
            windDirection = arr[responseRootObject.getJSONObject("current_weather").getFloat("winddirection").roundToInt()/45],
            windSpeed = responseRootObject.getJSONObject("current_weather").getFloat("windspeed").roundToInt(),
            minTemp = responseRootObject.getJSONObject("daily").getJSONArray("temperature_2m_min").getDouble(0).roundToInt(),
            maxTemp = responseRootObject.getJSONObject("daily").getJSONArray("temperature_2m_max").getDouble(0).roundToInt(),
            currentTemp = responseRootObject.getJSONObject("current_weather").getFloat("temperature").roundToInt(),
            humidity = responseRootObject.getJSONObject("hourly").getJSONArray("relativehumidity_2m").getInt(timeService.getCurrentHour())
        )
    }
}