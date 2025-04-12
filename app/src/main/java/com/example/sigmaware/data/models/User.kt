package com.example.sigmaware.data.models

data class User(
    val id: String = "",
    val cf: String = "",
    val fullName: String = "",
    val birthDate: String = "",
    val phone: String = "",
    val email: String = "",
    val paymentMethods: List<String> = emptyList()
)