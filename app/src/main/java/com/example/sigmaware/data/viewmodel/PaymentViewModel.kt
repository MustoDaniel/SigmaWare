package com.example.sigmaware.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.stripe.android.model.PaymentMethod
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PaymentViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    val paymentMethods = MutableStateFlow<List<String>>(emptyList())

    fun addPaymentMethod(paymentMethodId: String) {
        viewModelScope.launch {
            try {
                db.collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid)
                    .update("paymentMethods", FieldValue.arrayUnion(paymentMethodId))
                    .addOnSuccessListener {
                        // Aggiorna lo stato locale
                        paymentMethods.value += paymentMethodId
                    }
            } catch (e: Exception) {
                // Gestisci errore
            }
        }
    }
}