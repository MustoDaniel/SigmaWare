package com.example.sigmaware.data.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VicenzaMapActivity(navController: NavController) {
    val context = LocalContext.current
    val vicenzaCenter = LatLng(45.5486, 11.5474)

    // Transport points in Vicenza
    val transportPoints = listOf(
        TransportPoint("Stazione FS Vicenza", LatLng(45.5436, 11.5408)),
        TransportPoint("Stazione Autobus Vicenza", LatLng(45.5452, 11.5415)),
        TransportPoint("Piazzale Roma (Taxi)", LatLng(45.5489, 11.5467)),
        TransportPoint("Piazza Castello (Autobus)", LatLng(45.5502, 11.5431)),
        TransportPoint("Viale Milano (Autobus)", LatLng(45.5518, 11.5364))
    )

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Mappa Trasporti Vicenza") })
        }
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize()) {
            // Google Maps integration
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(vicenzaCenter, 13f)
            }

            GoogleMap(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                transportPoints.forEach { point ->
                    Marker(
                        state = MarkerState(position = point.location),
                        title = point.name,
                        snippet = "Punto di trasporto"
                    )
                }
            }

            Button(
                onClick = {
                    openGoogleMaps(context, vicenzaCenter)
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Apri in Google Maps")
            }
        }
    }
}

private fun openGoogleMaps(context: Context, location: LatLng) {
    val gmmIntentUri = Uri.parse("geo:${location.latitude},${location.longitude}?q=${location.latitude},${location.longitude}(Punti di trasporto Vicenza)")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    mapIntent.setPackage("com.google.android.apps.maps")
    context.startActivity(mapIntent)
}

data class TransportPoint(val name: String, val location: LatLng)