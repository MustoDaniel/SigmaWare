package com.example.sigmaware.data.models

import com.google.firebase.firestore.PropertyName
import com.google.type.LatLng

data class Transport(
    @get:PropertyName("line_number")
    @set:PropertyName("line_number")
    var lineNumber: String = "",

    @get:PropertyName("type")
    @set:PropertyName("type")
    var type: String = "",  // "bus", "tram", "train"

    @get:PropertyName("departure_time")
    @set:PropertyName("departure_time")
    var departureTime: String = "",  // Formato "HH:mm"

    @get:PropertyName("arrival_time")
    @set:PropertyName("arrival_time")
    var arrivalTime: String = "",  // Formato "HH:mm"

    @get:PropertyName("stops")
    @set:PropertyName("stops")
    var stops: List<String> = emptyList(),  // Lista fermate

    @get:PropertyName("route_coordinates")
    @set:PropertyName("route_coordinates")
    var routeCoordinates: List<Map<String, Double>> = emptyList(),  // Lista di LatLng

    @get:PropertyName("real_time_status")
    @set:PropertyName("real_time_status")
    var realTimeStatus: String = "on_time",  // "on_time", "delayed", "cancelled"

    @get:PropertyName("vehicle_id")
    @set:PropertyName("vehicle_id")
    var vehicleId: String = "",

    @get:PropertyName("operator")
    @set:PropertyName("operator")
    var operator: String = ""
) {
    // Funzione di utilità per ottenere le coordinate come List<LatLng>
    fun getLatLngRoute(): List<com.google.android.gms.maps.model.LatLng> {
        return routeCoordinates.map {
            com.google.android.gms.maps.model.LatLng(it["latitude"] ?: 0.0, it["longitude"] ?: 0.0)
        }
    }

    companion object {
        // Esempio dati per testing
        fun sampleBus(): Transport {
            return Transport(
                lineNumber = "5",
                type = "bus",
                departureTime = "08:15",
                arrivalTime = "08:45",
                stops = listOf("Stazione FS", "Piazza Matteotti", "Viale Roma"),
                routeCoordinates = listOf(
                    mapOf("latitude" to 45.5463, "longitude" to 11.5472),
                    mapOf("latitude" to 45.5487, "longitude" to 11.5491)
                ),
                realTimeStatus = "on_time",
                vehicleId = "BUS-005",
                operator = "SV Trasporti"
            )
        }
    }
}