package com.sd.laborator.controllers

import com.sd.laborator.services.FirewallService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class WeatherAppController {
//    @Autowired
//    private lateinit var locationSearchService: LocationSearchInterface
//
//    @Autowired
//    private lateinit var weatherForecastService: WeatherForecastInterface
    @Autowired
    private lateinit var  firewall : FirewallService

    private val blackList = listOf<String>("Romania", "Germany", "Bulgaria")

    @RequestMapping("/getforecast/{location}", method = [RequestMethod.GET])
    @ResponseBody
    fun getForecast(@PathVariable location: String): String {

        val locationId = firewall.getLocationId(location) ?: return "Nu s-au putut gasi date meteo pentru cuvintele cheie \"$location\"!"
        if(locationId.country in blackList)
            return "Nu se poate accesa locatia respectiva"

        return firewall.toHtml(location)

    }
}