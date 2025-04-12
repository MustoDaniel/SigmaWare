package com.example.sigmaware.ui.main.payment

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.stripe.android.view.CardInputWidget
import com.example.sigmaware.data.viewmodel.PaymentViewModel

@Composable
fun PaymentScreen(viewModel: PaymentViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    var cardValid by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Add Payment Method",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(24.dp))

        AndroidView(
            factory = { context ->
                CardInputWidget(context).apply {
                    setCardValidCallback { isValid -> cardValid = isValid }
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { viewModel.addPaymentMethod() },
            enabled = cardValid,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Card")
        }
    }
}