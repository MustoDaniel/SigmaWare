package com.example.sigmaware.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sigmaware.data.models.PollutionData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PollutionViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val _pollutionData = MutableStateFlow<List<PollutionData>>(emptyList())
    val pollutionData: StateFlow<List<PollutionData>> = _pollutionData

    init {
        loadPollutionData()
    }

    private fun loadPollutionData() {
        viewModelScope.launch {
            db.collection("pollution")
                .addSnapshotListener { snapshot, error ->
                    if (error != null) return@addSnapshotListener

                    val data = snapshot?.documents?.mapNotNull { doc ->
                        doc.toObject<PollutionData>()
                    } ?: emptyList()

                    _pollutionData.value = data
                }
        }
    }
}