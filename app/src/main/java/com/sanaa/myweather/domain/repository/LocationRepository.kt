package com.sanaa.myweather.domain.repository
import com.sanaa.myweather.domain.model.LocationInfo

interface LocationRepository{
    suspend fun getCurrentLocation(): LocationInfo
}