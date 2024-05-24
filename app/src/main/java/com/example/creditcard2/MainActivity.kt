package com.example.creditcard2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.creditcard2.ViewModel.CreditCardViewModel
import com.example.creditcard2.ui.theme.CreditCard2Theme

class MainActivity : ComponentActivity() {
    private val viewModel: CreditCardViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CreditCard2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ListCreditCard(
                        name = "Android", viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ListCreditCard(
    name: String,
    viewModel: CreditCardViewModel,
    modifier: Modifier = Modifier
) {
    val creditCards by viewModel.creditCard.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.fetchCreditCard()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
         Column {
            if (creditCards.isEmpty()) {
                Text(text = "Loading..")
                CircularProgressIndicator()
            } else {
                LazyColumn {
                    items(creditCards) { card ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .background(color = Color.LightGray)
                                .clip(RoundedCornerShape(8.dp))

                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp)
                            ) {
                                Text(text = "Name: ${card.name}")
                                Text(text = "Card Number: ${card.card_number}")
                                Text(text = "Card Type: ${card.card_type}")
                                Text(text = "Expiry Date: ${card.card_expiry_date}")
                            }
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                }
            }
        }
    }
}