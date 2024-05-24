package com.example.creditcard2.ViewModel

import com.example.creditcard2.Model.CreditCard
import com.example.creditcard2.Service.CreditCardService
import com.example.creditcard2.Service.RetrofitInstance

class CreditCardRepository {
    private val creditCardService: CreditCardService = RetrofitInstance.creditCardService

    suspend fun getCreditCard(): List<CreditCard> {
        return creditCardService.getCreditCard()
    }
}