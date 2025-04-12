package com.example.sigmaware.ui.main.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.sigmaware.data.models.AirQualityLevel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.example.sigmaware.data.viewmodel.PollutionViewModel

@Composable
fun PollutionMapScreen(viewModel: PollutionViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val pollutionData = viewModel.pollutionData.collectAsState().value
    val vicenza = LatLng(45.5480, 11.5475)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(vicenza, 12f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(mapType = MapType.NORMAL)
    ) {
        pollutionData.forEach { data ->
            Circle(
                center = data.getLatLng(),
                radius = 300.0,
                fillColor = when (data.calculateAQI()) {
                    AirQualityLevel.GOOD -> Color.Green.copy(alpha = 0.3f)
                    AirQualityLevel.MODERATE -> Color.Yellow.copy(alpha = 0.3f)
                    else -> Color.Red.copy(alpha = 0.3f)
                },
                strokeWidth = 2f,
                strokeColor = Color.Black
            )
        }
    }
}