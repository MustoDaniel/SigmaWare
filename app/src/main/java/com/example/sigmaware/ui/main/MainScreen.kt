package com.example.sigmaware.ui.main

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sigmaware.ui.main.emergency.EmergencyScreen
import com.example.sigmaware.ui.main.map.PollutionMapScreen
import com.example.sigmaware.ui.main.payment.PaymentScreen
import com.example.sigmaware.ui.main.transport.TransportScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "transport"
    ) {
        composable("transport") { TransportScreen(navController) }
        composable("pollution_map") { PollutionMapScreen() }
        composable("payment") { PaymentScreen() }
        composable("emergency") { EmergencyScreen() }
    }
}