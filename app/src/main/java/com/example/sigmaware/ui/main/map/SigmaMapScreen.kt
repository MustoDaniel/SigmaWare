package com.example.sigmaware.ui.main.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun SigmaMapScreen() {
    val vicenza = LatLng(45.5480, 11.5475)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(vicenza, 13f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(mapType = MapType.HYBRID)
    ) {
        // TODO: Aggiungere overlay personalizzato quando disponibile
        Marker(
            state = MarkerState(position = vicenza),
            title = "Sigma Vicenza",
            snippet = "Headquarters"
        )
    }
}