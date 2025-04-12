package com.example.sigmaware.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.example.sigmaware.data.models.*
import kotlinx.coroutines.tasks.await

class FirestoreRepository {
    private val db = FirebaseFirestore.getInstance()

    suspend fun getTransports(): List<Transport> {
        return db.collection("transports")
            .get()
            .await()
            .toObjects(Transport::class.java)
    }
}