package com.sanaa.myweather.data.remote

import android.Manifest
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.annotation.RequiresPermission
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority
import com.sanaa.myweather.domain.model.CityNameNotFoundException
import com.sanaa.myweather.domain.model.ErrorFetchingUserLocationException
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resumeWithException

class GoogleLocationService(
    private val fusedLocationClient: FusedLocationProviderClient,
    private val geocoder: Geocoder
) {


    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    suspend fun getCurrentLocation(): Location {
        return try {
            val location = fusedLocationClient.lastLocation.await()
            return location ?: getFreshLocation()
        } catch (_: Exception) {
            throw ErrorFetchingUserLocationException()
        }
    }

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    private suspend fun getFreshLocation(): Location = suspendCancellableCoroutine { cont ->
        val request = LocationRequest.create().apply {
            priority = Priority.PRIORITY_HIGH_ACCURACY
            interval = 1000
            fastestInterval = 500
            numUpdates = 1
        }
        val callback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                fusedLocationClient.removeLocationUpdates(this)
                val location = result.lastLocation
                if (location != null) {
                    cont.resume(location, null)
                } else {
                    cont.resumeWithException(Exception())
                }
            }
        }
        fusedLocationClient.requestLocationUpdates(request, callback, null)
    }

    fun getCityNameFromLatLng(latitude: Double, longitude: Double): String {
        return try {
            val addresses: List<Address>? = geocoder.getFromLocation(latitude, longitude, 1)

            if (!addresses.isNullOrEmpty()) {
                addresses[0].locality
                    ?: addresses[0].subAdminArea
                    ?: addresses[0].adminArea
            } else {
                throw CityNameNotFoundException()
            }
        } catch (_: Exception) {
            throw ErrorFetchingUserLocationException()
        }
    }
}