package com.example.sigmaware.data.activities

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentActivity(navController: NavController) {
    val paymentMethods = listOf("Carta di Credito", "PayPal")
    val (selectedMethod, setSelectedMethod) = remember { mutableStateOf(paymentMethods[0]) }

    // Credit card fields
    val cardNumber = remember { mutableStateOf("") }
    val cardHolder = remember { mutableStateOf("") }
    val expiryDate = remember { mutableStateOf("") }
    val cvv = remember { mutableStateOf("") }

    // PayPal fields
    val paypalEmail = remember { mutableStateOf("") }
    val paypalPassword = remember { mutableStateOf("") }

    val savePaymentInfo = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Pagamento Biglietto") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            Text("Seleziona metodo di pagamento:", modifier = Modifier.padding(16.dp))

            paymentMethods.forEach { method ->
                Column {
                    RadioButton(
                        selected = selectedMethod == method,
                        onClick = { setSelectedMethod(method) }
                    )
                    Text(text = method)
                }
            }

            when (selectedMethod) {
                "Carta di Credito" -> {
                    OutlinedTextField(
                        value = cardNumber.value,
                        onValueChange = { cardNumber.value = it },
                        label = { Text("Numero Carta") },
                        modifier = Modifier.padding(16.dp)
                    )
                    OutlinedTextField(
                        value = cardHolder.value,
                        onValueChange = { cardHolder.value = it },
                        label = { Text("Intestatario Carta") },
                        modifier = Modifier.padding(16.dp)
                    )
                    OutlinedTextField(
                        value = expiryDate.value,
                        onValueChange = { expiryDate.value = it },
                        label = { Text("Data Scadenza (MM/AA)") },
                        modifier = Modifier.padding(16.dp)
                    )
                    OutlinedTextField(
                        value = cvv.value,
                        onValueChange = { cvv.value = it },
                        label = { Text("CVV") },
                        modifier = Modifier.padding(16.dp)
                    )
                }
                "PayPal" -> {
                    OutlinedTextField(
                        value = paypalEmail.value,
                        onValueChange = { paypalEmail.value = it },
                        label = { Text("Email PayPal") },
                        modifier = Modifier.padding(16.dp)
                    )
                    OutlinedTextField(
                        value = paypalPassword.value,
                        onValueChange = { paypalPassword.value = it },
                        label = { Text("Password PayPal") },
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            Checkbox(
                checked = savePaymentInfo.value,
                onCheckedChange = { savePaymentInfo.value = it },
                modifier = Modifier.padding(16.dp)
            )
            Text("Salva informazioni per acquisti futuri", modifier = Modifier.padding(start = 16.dp))

            Button(
                onClick = {
                    // Process payment logic here
                    navController.popBackStack()
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Conferma Pagamento")
            }
        }
    }
}