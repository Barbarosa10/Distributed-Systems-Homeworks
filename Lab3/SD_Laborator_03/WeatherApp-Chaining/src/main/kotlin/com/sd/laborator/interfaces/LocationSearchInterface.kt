package com.sd.laborator.interfaces

import com.sd.laborator.pojo.PositionData

interface LocationSearchInterface {
    fun getLocationId(locationName: String): PositionData?
}