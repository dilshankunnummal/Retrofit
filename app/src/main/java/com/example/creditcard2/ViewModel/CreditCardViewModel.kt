package com.example.creditcard2.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.creditcard2.Model.CreditCard
import kotlinx.coroutines.launch

class CreditCardViewModel : ViewModel() {
    private val repository = CreditCardRepository()

    private val _creditCard = MutableLiveData<List<CreditCard>>()

    val creditCard: LiveData<List<CreditCard>> = _creditCard

    fun fetchCreditCard() {
        viewModelScope.launch {
            try {
                val list = repository.getCreditCard()
                _creditCard.value = list
            } catch (e: Exception) {
                Log.e(" Retrofit Error", e.toString())
                e.printStackTrace()
            }
        }
    }
}