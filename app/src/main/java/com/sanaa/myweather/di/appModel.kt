package com.sanaa.myweather.di

import android.location.Geocoder
import com.google.android.gms.location.LocationServices
import com.sanaa.myweather.data.remote.GoogleLocationService
import com.sanaa.myweather.data.remote.OpenMeteWeatherApi
import com.sanaa.myweather.data.repository.LocationRepositoryImpl
import com.sanaa.myweather.data.repository.WeatherRepositoryImpl
import com.sanaa.myweather.domain.repository.LocationRepository
import com.sanaa.myweather.domain.repository.WeatherRepository
import com.sanaa.myweather.domain.usecase.GetCurrentLocationUseCase
import com.sanaa.myweather.domain.usecase.GetWeatherInfoUseCase
import com.sanaa.myweather.presentation.viewModel.WeatherViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import java.util.Locale

val appModule = module {


    // Date Source
    single {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
        }
    }

    single {
        OpenMeteWeatherApi(get())
    }
    single { LocationServices.getFusedLocationProviderClient(androidContext()) }
    single { Geocoder(androidContext(), Locale.getDefault()) }
    single { GoogleLocationService(get(), get()) }


    // Repository
    single<LocationRepository> { LocationRepositoryImpl(get()) }
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }

    // UseCase
    single {
        GetWeatherInfoUseCase(get())
    }
    single { GetCurrentLocationUseCase(get()) }


    // View model
    viewModel {
        WeatherViewModel(get(), get())
    }

}