package com.sd.laborator.services

import com.sd.laborator.interfaces.WeatherPageCreatorInterface
import com.sd.laborator.pojo.WeatherForecastData
import org.springframework.stereotype.Service

@Service
class WeatherPageCreatorService : WeatherPageCreatorInterface {
    private var htmlPageCode = String()

    override fun initPage(){
        htmlPageCode = ""
        htmlPageCode += "<!doctype html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "<meta charset=\"utf-8\">" +
                "<title>Weather Application</title>" +
                "</head>" +
                "<body>"

    }
    override fun fillPage(forecastData: WeatherForecastData) {
        htmlPageCode += "<h1>Locatie: " + forecastData.location + " la data " + forecastData.date + "</h1>"
        htmlPageCode += "<table border=\"1\">"
//        htmlPageCode += "<tr><td>Stare Vreme</td><td>" + forecastData.weatherState + "</td></tr>"
        htmlPageCode += "<tr><td>Directie vant</td><td>" + forecastData.windDirection + "</td></tr>"
        htmlPageCode += "<tr><td>Viteza Vant</td><td>" + forecastData.windSpeed.toString() + "km/h</td></tr>"
        htmlPageCode += "<tr><td>Temperatura minima</td><td>" + forecastData.minTemp + "°C</td></tr>"
        htmlPageCode += "<tr><td>Temperatura maxima</td><td>" + forecastData.maxTemp + "°C</td></tr>"
        htmlPageCode += "<tr><td>Temperatura curenta</td><td>" + forecastData.currentTemp + "°C</td></tr>"
        htmlPageCode += "<tr><td>Umiditate</td><td>" + forecastData.humidity + "%</td></tr>"
        htmlPageCode += "</table>"
    }
    override fun finishPage() {
        htmlPageCode += "</body>"
        htmlPageCode += "</html>"
    }
    override fun returnPage() : String {
        return htmlPageCode
    }
    override fun addElementToPage(element : String){
        htmlPageCode += element
    }

}