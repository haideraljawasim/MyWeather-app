package com.sanaa.myweather.data.repository

import android.Manifest
import androidx.annotation.RequiresPermission
import com.sanaa.myweather.data.remote.GoogleLocationService
import com.sanaa.myweather.domain.model.LocationInfo
import com.sanaa.myweather.domain.repository.LocationRepository

class LocationRepositoryImpl(
    private val locationDataSource: GoogleLocationService
) : LocationRepository {


    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    override suspend fun getCurrentLocation(): LocationInfo {
        val location = locationDataSource.getCurrentLocation()
        val cityName =
            locationDataSource.getCityNameFromLatLng(location.latitude, location.longitude)

        return LocationInfo(
            latitude = location.latitude,
            longitude = location.longitude,
            cityName = cityName
        )
    }
}
