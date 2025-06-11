package com.sanaa.myweather.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.sanaa.myweather.presentation.screen.WeatherScreen
import com.sanaa.myweather.presentation.viewModel.WeatherViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val viewModel: WeatherViewModel = getViewModel()
            var hasPermission by remember { mutableStateOf(false) }
            val permissionLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestMultiplePermissions()
            ) { permissions ->
                val fine = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true
                val coarse = permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
                hasPermission = fine || coarse
            }
            LaunchedEffect(Unit) {
                val fine = ContextCompat.checkSelfPermission(
                    context, Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
                val coarse = ContextCompat.checkSelfPermission(
                    context, Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
                if (fine || coarse) {
                    hasPermission = true
                } else {
                    permissionLauncher.launch(
                        arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        )
                    )
                }
            }
            LaunchedEffect(hasPermission) {
                if (hasPermission) {
                    viewModel.loadWeather()
                }
            }
            Scaffold { innerPadding ->
                WeatherScreen(inPadding = innerPadding)
            }

        }
    }
}