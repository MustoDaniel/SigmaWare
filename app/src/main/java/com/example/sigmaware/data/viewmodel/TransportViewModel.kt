package com.example.sigmaware.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sigmaware.data.models.Transport
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TransportViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val _transports = MutableStateFlow<List<Transport>>(emptyList())
    private val _filteredTransports = MutableStateFlow<List<Transport>>(emptyList())

    val transports: StateFlow<List<Transport>> = _transports
    val filteredTransports: StateFlow<List<Transport>> = _filteredTransports

    init {
        loadTransports()
    }

    private fun loadTransports() {
        viewModelScope.launch {
            db.collection("transports")
                .addSnapshotListener { snapshot, error ->
                    if (error != null) return@addSnapshotListener

                    val transportsList = snapshot?.documents?.mapNotNull { doc ->
                        doc.toObject<Transport>()?.copy(doc.id)
                    } ?: emptyList()

                    _transports.value = transportsList
                    _filteredTransports.value = transportsList
                }
        }
    }

    fun filterTransports(query: String) {
        _filteredTransports.value = _transports.value.filter {
            it.lineNumber.contains(query, true) ||
                    it.stops.any { stop -> stop.contains(query, true) }
        }
    }
}