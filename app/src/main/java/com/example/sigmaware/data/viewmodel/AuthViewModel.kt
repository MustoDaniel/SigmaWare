package com.example.sigmaware.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    val currentUser = auth.currentUser
    val errorMessage = MutableStateFlow<String?>(null)

    fun registerUser(
        cf: String,
        fullName: String,
        birthDate: String,
        phone: String,
        email: String,
        password: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            try {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = hashMapOf(
                            "cf" to cf,
                            "fullName" to fullName,
                            "birthDate" to birthDate,
                            "phone" to phone,
                            "email" to email
                        )

                        db.collection("users").document(auth.currentUser!!.uid)
                            .set(user)
                            .addOnSuccessListener { onSuccess() }
                    } else {
                        errorMessage.value = task.exception?.message
                    }
                }
            } catch (e: Exception) {
                errorMessage.value = e.message
            }
        }
    }

    fun loginUser(email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            onSuccess()
                        } else {
                            errorMessage.value = task.exception?.message
                        }
                    }
            } catch (e: Exception) {
                errorMessage.value = e.message
            }
        }
    }
}