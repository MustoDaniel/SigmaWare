package com.example.sigmaware.data.repository

import com.example.sigmaware.data.models.Transport

interface TransportRepository {
    suspend fun getAllTransports(): List<Transport>
    suspend fun filterTransports(query: String): List<Transport>
}

class TransportRepositoryImpl : TransportRepository {
    override suspend fun getAllTransports(): List<Transport> {
        // Implementa chiamata API reale
        return emptyList()
    }

    override suspend fun filterTransports(query: String): List<Transport> {
        // Implementa filtro
        return emptyList()
    }
}