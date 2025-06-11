package com.sanaa.myweather.domain.usecase

import com.sanaa.myweather.domain.model.LocationInfo
import com.sanaa.myweather.domain.repository.LocationRepository

class GetCurrentLocationUseCase(
    private val locationRepository: LocationRepository
) {
    suspend fun getCurrentLocation(): LocationInfo {
        return locationRepository.getCurrentLocation()
    }
}