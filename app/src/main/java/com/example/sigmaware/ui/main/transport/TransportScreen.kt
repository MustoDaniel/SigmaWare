package com.example.sigmaware.ui.main.transport

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sigmaware.data.models.*
import com.example.sigmaware.data.viewmodel.*

@Composable
fun TransportScreen() {
    val viewModel: TransportViewModel = viewModel()
    val transports by viewModel.filteredTransports.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                viewModel.filterTransports(it)
            },
            label = { Text("Cerca mezzo...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(transports) { transport ->
                TransportItem(transport = transport)
            }
        }
    }
}

@Composable
fun TransportItem(transport: Transport) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "${transport.type} - Linea ${transport.lineNumber}",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("Partenza: ${transport.departureTime}")
            Text("Arrivo: ${transport.arrivalTime}")
            Text("Percorso: ${transport.stops.joinToString(" → ")}")
        }
    }
}