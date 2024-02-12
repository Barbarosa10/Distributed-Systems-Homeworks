package com.sd.laborator.services

import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*

@Service
class TimeService {
    fun getCurrentTime():String {
        val formatter =  SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        return formatter.format(Date())
    }
    fun getCurrentHour(): Int{
        val formatter =  SimpleDateFormat("HH")
        return formatter.format(Date()).toInt()
    }
}