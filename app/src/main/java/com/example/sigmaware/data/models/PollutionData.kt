package com.example.sigmaware.data.models

import com.google.firebase.firestore.PropertyName
import com.google.android.gms.maps.model.LatLng
import java.util.Date

data class PollutionData(
    @get:PropertyName("location")
    @set:PropertyName("location")
    var location: Map<String, Double> = emptyMap(), // {latitude: 45.548, longitude: 11.547}

    @get:PropertyName("pm2_5")
    @set:PropertyName("pm2_5")
    var pm25: Double = 0.0, // Particulate Matter 2.5

    @get:PropertyName("pm10")
    @set:PropertyName("pm10")
    var pm10: Double = 0.0, // Particulate Matter 10

    @get:PropertyName("no2")
    @set:PropertyName("no2")
    var no2: Double = 0.0, // Nitrogen Dioxide

    @get:PropertyName("o3")
    @set:PropertyName("o3")
    var o3: Double = 0.0, // Ozone

    @get:PropertyName("co")
    @set:PropertyName("co")
    var co: Double = 0.0, // Carbon Monoxide

    @get:PropertyName("so2")
    @set:PropertyName("so2")
    var so2: Double = 0.0, // Sulfur Dioxide

    @get:PropertyName("temperature")
    @set:PropertyName("temperature")
    var temperature: Double = 0.0, // Temperatura in °C

    @get:PropertyName("humidity")
    @set:PropertyName("humidity")
    var humidity: Double = 0.0, // Umidità %

    @get:PropertyName("timestamp")
    @set:PropertyName("timestamp")
    var timestamp: Date = Date() // Data rilevazione

) {
    // Restituisce le coordinate come LatLng
    fun getLatLng(): LatLng {
        return LatLng(
            location["latitude"] ?: 0.0,
            location["longitude"] ?: 0.0
        )
    }

    // Calcola l'indice di qualità dell'aria (AQI)
    fun calculateAQI(): AirQualityLevel {
        return when (pm25) {
            in 0.0..12.0 -> AirQualityLevel.GOOD
            in 12.1..35.4 -> AirQualityLevel.MODERATE
            in 35.5..55.4 -> AirQualityLevel.UNHEALTHY_SENSITIVE
            in 55.5..150.4 -> AirQualityLevel.UNHEALTHY
            else -> AirQualityLevel.HAZARDOUS
        }
    }

    companion object {
        // Esempio dati per testing
        fun sampleData(): PollutionData {
            return PollutionData(
                location = mapOf("latitude" to 45.548, "longitude" to 11.547),
                pm25 = 15.3,
                pm10 = 25.7,
                no2 = 30.0,
                o3 = 45.0,
                co = 0.5,
                so2 = 10.2,
                temperature = 18.5,
                humidity = 65.0,
                timestamp = Date()
            )
        }
    }
}

enum class AirQualityLevel(val description: String) {
    GOOD("Buona"),
    MODERATE("Moderata"),
    UNHEALTHY_SENSITIVE("Malsana per gruppi sensibili"),
    UNHEALTHY("Malsana"),
    VERY_UNHEALTHY("Molto malsana"),
    HAZARDOUS("Pericolosa")
}