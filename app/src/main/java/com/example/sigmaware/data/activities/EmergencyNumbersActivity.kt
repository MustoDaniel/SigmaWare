package com.example.sigmaware.data.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmergencyNumbersActivity(navController: NavController) {
    val context = LocalContext.current
    val permissionState = remember { mutableStateOf(false) }

    val callPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        permissionState.value = isGranted
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Numeri di Emergenza") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            EmergencyButton(
                name = "Polizia",
                number = "113",
                permissionState.value,
                onClick = {
                    if (ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.CALL_PHONE
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        val intent = Intent(Intent.ACTION_CALL).apply {
                            data = Uri.parse("tel:113")
                        }
                        context.startActivity(intent)
                    } else {
                        callPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
                    }
                }
            )

            EmergencyButton(
                name = "Carabinieri",
                number = "112",
                permissionState.value,
                onClick = {
                    if (ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.CALL_PHONE
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        val intent = Intent(Intent.ACTION_CALL).apply {
                            data = Uri.parse("tel:112")
                        }
                        context.startActivity(intent)
                    }
                }
            )

            EmergencyButton(
                name = "Emergenza Sanitaria",
                number = "118",
                permissionState.value,
                onClick = {
                    if (ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.CALL_PHONE
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        val intent = Intent(Intent.ACTION_CALL).apply {
                            data = Uri.parse("tel:118")
                        }
                        context.startActivity(intent)
                    }
                }
            )

            EmergencyButton(
                name = "Vigili del Fuoco",
                number = "115",
                permissionState.value,
                onClick = {
                    if (ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.CALL_PHONE
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        val intent = Intent(Intent.ACTION_CALL).apply {
                            data = Uri.parse("tel:115")
                        }
                        context.startActivity(intent)
                    }
                }
            )

            EmergencyButton(
                name = "Emergenza in Mare",
                number = "1530",
                permissionState.value,
                onClick = {
                    if (ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.CALL_PHONE
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        val intent = Intent(Intent.ACTION_CALL).apply {
                            data = Uri.parse("tel:1530")
                        }
                        context.startActivity(intent)
                    }
                }
            )

            EmergencyButton(
                name = "Numero Unico Emergenza UE",
                number = "112",
                permissionState.value,
                onClick = {
                    if (ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.CALL_PHONE
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        val intent = Intent(Intent.ACTION_CALL).apply {
                            data = Uri.parse("tel:112")
                        }
                        context.startActivity(intent)
                    }
                }
            )
        }
    }
}

@Composable
fun EmergencyButton(name: String, number: String, hasPermission: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Column {
            Text(text = name)
            Text(text = number)
            if (!hasPermission) {
                Text(text = "Permesso chiamata necessario", color = Red)
            }
        }
    }
}